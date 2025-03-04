package com.uitgis.ciams.service;

import com.daonsw.spatial.adpater.mapstudio.dto.MapStudioResult;
import com.daonsw.spatial.dto.LayerFileInfo;
import com.daonsw.spatial.dto.TableSchemaDto;
import com.daonsw.spatial.util.DateUtil;
import com.uitgis.ciams.dto.KrasMultiPartFile;
import com.uitgis.ciams.enums.KrasContentTypeEnum;
import com.uitgis.ciams.exception.CustomException;
import com.uitgis.ciams.model.CiamsConfig;
import com.uitgis.ciams.util.FileUtil;
import com.uitgis.ext.kras.KrasException;
import com.uitgis.ext.kras.KrasService;
import com.uitgis.ext.kras.dto.KrasConfig;
import com.uitgis.ext.kras.dto.KrasResult;
import com.uitgis.gis.dto.UploadDataDto;
import com.uitgis.gis.mapper.MapStudio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CiamsKrasService {
    @Value("${crs.prj}")
    private String prj;

    @Value("${crs.use-transform}")
    private boolean useTransform;

    @Value("${crs.use-badekas}")
    private boolean useBadekas;

    private final MapStudio mapStudio;
    private final CiamsGisService ciamsGisService;
    private final CiamsConfigService ciamsConfigService;


    public void renewalKrasData() throws CustomException, IOException {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String suffix = now.format(dateTimeFormatter);

        String krasLayer = "LSMD_CONT_LDREG";
        String ciamsLayer = "CIAMS_LSMD_CONT_LDREG";
//        String uploadLayer = krasLayer + "_" + suffix + ".zip";

        List<String> krasLayerCdList = new ArrayList<>(Collections.singletonList(krasLayer));


        log.info("[Renewal] Download target kras layer cd list [{}]", krasLayerCdList);

        Path prjFilePath = getPrjFilePath();

        KrasConfig.ShpParam shpParam = getKrasShpParam();
        shpParam.setLayerCdList(krasLayerCdList);
        shpParam.setPrjFilePath(prjFilePath);

        log.info("[Renewal] Download shp zip files...");
        KrasService krasService = new KrasService();

        int succ = 0;
        List<String> errFileList = new ArrayList<>();
        List<KrasResult> krasResultList = null;

        try {
            krasResultList = krasService.getShapeFile(shpParam);

            for (String layerName : krasLayerCdList) {
                log.info("[Renewal] Create renewal history...layerName :{}", layerName);
//				BsDataRenewalHistory history = addInitialKrasScheduledHistory(layerName);

                KrasResult result = krasResultList.stream().filter(krasResult -> krasResult.getLayerName().equals(layerName)).findAny().orElse(null);

                if (result != null) {
                    Path filePath = result.getLayerPath();

                    if (filePath != null && Files.exists(filePath) && Files.size(filePath) > 0) {
                        String fileName = filePath.getFileName().toString();
//                        String fileName = uploadLayer;
                        FileItem fileItem = new DiskFileItemFactory().createItem("file", Files.probeContentType(filePath), false, fileName);

                        try (InputStream in = Files.newInputStream(filePath); OutputStream out = fileItem.getOutputStream()) {
                            IOUtils.copy(in, out);
//                            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
                            MultipartFile multipartFile = new KrasMultiPartFile(filePath, KrasContentTypeEnum.ZIP);

                            log.info("[Renewal] Upload shp to spatial server. [{}]", filePath);

                            UploadDataDto.Upload param = UploadDataDto.Upload.builder()
                                    .encoding("x-windows-949")
                                    .file(multipartFile)
                                    .build();

                            // 좌표 변환
                            if (useTransform) {
                                param.setCrs("EPSG:5186");
                            }

                            // 타원체 변환
                            if (useBadekas) {
                                param.setBadekas(LayerFileInfo.BadekasEnum.forward.name());
                            }

                            MapStudioResult.LayerBody uploadResult = ciamsGisService.uploadKrasLayer(param);

                            if (!uploadResult.getIsException() && uploadResult.getSuccess().equals("true")) {
                                String uploadLayer = uploadResult.getData().get(0);
                                log.info("[Renewal] MapStudio Layer Upload [{}]", uploadLayer);

                                if (mapStudio.layerExists(ciamsLayer)) {
                                    layerRename(ciamsLayer, ciamsLayer + "_" + suffix);
                                }

                                layerRename(uploadLayer, ciamsLayer);

                                log.info("[Renewal] MapStudio Layer Delete [{}]", uploadLayer);
                                ciamsGisService.deleteLayer(uploadLayer);

                                log.info("[Renewal] MapStudio Layer Delete [{}]", ciamsLayer + "_" + suffix);
                                mapStudio.deleteLayer(ciamsLayer + "_" + suffix);
                            }

                            // 등록 성공한 파일은 삭제
                            deleteKrasLayerFile(layerName, filePath);
                        } catch (Exception e) {
                            String msg = "Uploading layer on spatial server error ";
//							history.setStatus(ERROR.getStatus());
//							history.setMsg(msg);
                            log.error("[Renewal] Upload shp error.[{}]", filePath, e);
                        }

                    } else {
                        log.info("[Renewal] file size is zero or file does not exist. path {}", filePath);
//						history.setStatus(NOT_FOUND.getStatus());
                    }
                } else {
                    log.info("[Renewal] kras layer download failed. layerCd {}", layerName);
//					history.setStatus(ERROR.getStatus());
                }
//				bsDataRenewalHistoryMapper.updateByNo(history);
            }
        } catch (KrasException e) {
//			addKrasServerErrorHistory(krasLayerCdList);
            log.error("Kras Server connection fail. connection param {}", shpParam, e);
        } catch (IOException e) {
            log.error("File ERROR :: ", e);
        }

        log.info("[Renewal] Kras layer renewal end. ");
        log.info("[Renewal] Success [{}]", succ);
        log.info("[Renewal] error file list [{}]", errFileList);
    }

    private Path getPrjFilePath() throws IOException {
//        String prjResourcePath = "epsg5186.prj";
        String prjResourcePath = "epsg" + prj.split(":")[1] + ".prj";
        return new ClassPathResource(prjResourcePath).getFile().toPath();
    }


    private KrasConfig.ShpParam getKrasShpParam() throws IOException {
        CiamsConfig KRAS_URL = ciamsConfigService.getConfById("KRAS_URL").orElse(null);
        CiamsConfig KRAS_SYS_ID = ciamsConfigService.getConfById("KRAS_SYS_ID").orElse(null);
        CiamsConfig SIDO_CODE = ciamsConfigService.getConfById("SIDO_CODE").orElse(null);
        CiamsConfig SGG_CODE = ciamsConfigService.getConfById("SGG_CODE").orElse(null);

        String krasSysId = KRAS_SYS_ID.getConfValue();
        String krasSvcUrl = KRAS_URL.getConfValue();
        String admSectCd = SIDO_CODE.getConfValue() + SGG_CODE.getConfValue();

//        String krasSysId = "S5AA-YYI4-JFJQ-50K2";
//        String krasSvcUrl = "http://127.0.0.1:8385/conn/estateGateway";
//        String admSectCd = "47850";

        KrasConfig.ShpParam shpParam = new KrasConfig.ShpParam();
        shpParam.setKey(krasSysId);
        shpParam.setUrl(krasSvcUrl);
        shpParam.setAdmSectCd(admSectCd);
        shpParam.setDownloadPath(getKrasFileDownPath());

        return shpParam;
    }

    private String getKrasFileDownPath() throws IOException {
        Path tempPath = Paths.get(FileUtil.getPathPrefix() + FileUtil.getFileTempPath());
        String workingDate = DateUtil.getCurrentDate("yyyyMMdd");
        tempPath = tempPath.resolve(workingDate);

        if (Files.notExists(tempPath)) {
            log.info("[Renewal] Kras temp file path does not exist. Create directories {}", tempPath);
            Files.createDirectories(tempPath);
        }
        return tempPath.toString();
    }


    private void deleteKrasLayerFile(String layerName, Path filePath) throws IOException {
        log.info("[Renewal] Delete download shp file... [{}]", filePath);
        Files.deleteIfExists(filePath);

        Path deleteFilePath = filePath.getParent().resolve(layerName + ".shp");
        log.info("[Renewal] Try to delete download shp file... [{}]", deleteFilePath);
        Files.deleteIfExists(deleteFilePath);
        deleteFilePath = filePath.getParent().resolve(layerName + ".prj");
        log.info("[Renewal] Try to delete download shp file... [{}]", deleteFilePath);
        Files.deleteIfExists(deleteFilePath);
        deleteFilePath = filePath.getParent().resolve(layerName + ".shx");
        log.info("[Renewal] Try to delete download shp file... [{}]", deleteFilePath);
        Files.deleteIfExists(deleteFilePath);
        deleteFilePath = filePath.getParent().resolve(layerName + ".dbf");
        log.info("[Renewal] Try to delete download shp file... [{}]", deleteFilePath);
        Files.deleteIfExists(deleteFilePath);
    }

    private void layerRename(String org, String target) throws CustomException {
        TableSchemaDto.Rename rename = TableSchemaDto.Rename.builder().orgTableName(org).newTableName(target).build();

        try {
            mapStudio.layerRename(rename);
        } catch (Exception e) {
            throw new CustomException("Layer Rename Error");
        }
    }
}
