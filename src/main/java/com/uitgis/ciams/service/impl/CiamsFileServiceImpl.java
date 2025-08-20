package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.model.CiamsFile;
import com.uitgis.ciams.service.CiamsFileService;
import com.uitgis.ciams.user.dto.CiamsFileDto;
import com.uitgis.ciams.user.dto.PaginationDto;
import com.uitgis.ciams.user.mapper.CiamsFileMapper;
import com.uitgis.ciams.util.FileUtil;
import com.uitgis.ciams.util.PageUtil;
import com.uitgis.ciams.util.StringUtil;
import com.uitgis.ciams.util.ValidUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CiamsFileServiceImpl implements CiamsFileService {
    private final CiamsFileMapper ciamsFileMapper;


    @Override
    public CiamsFile selectById(String id) {
        return ciamsFileMapper.selectById(id);
    }


    @Override
    public List<CiamsFileDto.TempFile> uploadTempFiles(List<MultipartFile> files) throws Exception {
        return uploadTempFiles(files, "etc");
    }


    @Override
    public List<CiamsFileDto.TempFile> uploadTempFiles(List<MultipartFile> files, String typeCode) throws Exception {
        List<CiamsFileDto.TempFile> tempFiles = new ArrayList<>();

        if (ValidUtil.empty(files)) {
            throw new NullPointerException("NULL");
        }

        String filePath = FileUtil.getPathPrefix() + FileUtil.getFileTempPath();

        if (!Files.exists(Paths.get(filePath))) {
            Files.createDirectories(Paths.get(filePath));
        }

        for (MultipartFile file : files) {
            String fileId = UUID.randomUUID().toString();
            String orgName = file.getOriginalFilename();
            String fileExt = StringUtil.getFileExt(orgName);
            String newName = fileId + "." + fileExt;

            /**
             * - ToDo : 타입, 사이즈 체크
             */
            try {
                file.transferTo(Paths.get(filePath + "/" + newName));
            } catch (IOException e) {
                log.error(e.getMessage());
                continue;
            }

            /**
             * -ToDo : 파일 타입
             */
            CiamsFileDto.TempFile tempFile = CiamsFileDto.TempFile.builder()
                    .orgName(orgName)
                    .newName(newName)
                    .size(file.getSize())
                    .ext(StringUtil.getFileExt(orgName))
                    .typeCode(typeCode)
                    .build();

            tempFiles.add(tempFile);
        }

        return tempFiles;
    }


    @Override
    public int uploadFiles(List<MultipartFile> files, String typeCode, String linkId) throws Exception {
        List<CiamsFile> fileList = new ArrayList<>();

        if (ValidUtil.empty(files)) {
            throw new NullPointerException("NULL");
        }

        String filePath = FileUtil.getPathPrefix() + "/" + typeCode;

        if (!Files.exists(Paths.get(filePath))) {
            Files.createDirectories(Paths.get(filePath));
        }

        List<String> linkIds = new ArrayList<>();
        linkIds.add(linkId);
        List<CiamsFile> list = ciamsFileMapper.selectByLinkIds(linkIds);
        int maxSortnum = 0;
        if(list.size() > 0 ) {
        	maxSortnum = list.stream()
        						.max(Comparator.comparingInt(CiamsFile::getSortSn))
        						.get()
        						.getSortSn();
        }


        for (MultipartFile file : files) {
            String fileId = UUID.randomUUID().toString();
            String orgName = file.getOriginalFilename();
            String fileExt = StringUtil.getFileExt(orgName);
            String newName = fileId + "." + fileExt;

            /**
             * - ToDo : 타입, 사이즈 체크
             */
            file.transferTo(Paths.get(filePath + "/" + newName));

            /**
             * -ToDo : 파일 타입
             */
            CiamsFile _file = CiamsFile.builder()
            							.id(fileId)
            							.orgName(orgName)
            							.newName(newName)
            							.size(file.getSize())
            							.ext(StringUtil.getFileExt(orgName))
            							.path("/" + typeCode)
            							.typeCode(typeCode)
            							.linkId(linkId)
            							.sortSn(++maxSortnum)
            							.build();

            fileList.add(_file);
        }

        return ciamsFileMapper.insertFiles(fileList);
    }


    @Override
    @Transactional
    public int insertFiles(List<CiamsFile> inserts) throws IOException {
        int result = 0;

        if (ValidUtil.notEmpty(inserts)) {
            List<CiamsFile> saveFiles = FileUtil.saveFiles(inserts);

            if (ValidUtil.notEmpty(saveFiles)) {
                result = ciamsFileMapper.insertFiles(saveFiles);
            }
        }

        return result;
    }


    @Override
    public int updateFiles(List<CiamsFile> updates) {
        int result = 0;

        if (ValidUtil.notEmpty(updates)) {
            result = ciamsFileMapper.updateFiles(updates);
        }

        return result;
    }


    /**
     * 첨부파일 sortSn 재정렬
     */
    @Override
    public int updateFilesSortSn(String linkId) {
    	int result = 0;

    	List<String> linkIds = new ArrayList<>();
        linkIds.add(linkId);
        List<CiamsFile> list = ciamsFileMapper.selectByLinkIds(linkIds);
        int i = 1;
        for(CiamsFile file : list) {
        	file.setSortSn(i++);
        }

    	if (ValidUtil.notEmpty(list)) {
    		result = ciamsFileMapper.updateFilesSortSn(list);
    	}

    	return result;
    }


    @Override
    @Transactional
    public int deleteFilesByLinkIds(List<String> linkIds) throws IOException {
        int result = 0;

        if (ValidUtil.notEmpty(linkIds)) {
            List<CiamsFile> list = ciamsFileMapper.selectByLinkIds(linkIds);
            FileUtil.deleteFiles(list);
            if (ValidUtil.notEmpty(list)) {
                result = ciamsFileMapper.deleteFiles(list);
            }
        }

        return result;
    }


    @Override
    @Transactional
    public int deleteFilesByIds(List<String> ids) throws IOException {
        int result = 0;

        if (ValidUtil.notEmpty(ids)) {
            List<CiamsFile> list = ciamsFileMapper.selectByIds(ids);
            FileUtil.deleteFiles(list);
            if (ValidUtil.notEmpty(list)) {
                result = ciamsFileMapper.deleteFiles(list);
            }
        }

        return result;
    }

    @Override
    public int searchCount(CiamsFileDto.Search.Params params) {
        return ciamsFileMapper.searchCount(params);
    }


    @Override
    public CiamsFileDto.Search.Result search(CiamsFileDto.Search.Params params) {
        int totalCount = ciamsFileMapper.searchCount(params);
        PaginationDto page = PageUtil.setTotalCount(params, totalCount);
        List<CiamsFileDto.Search.Row> rows = ciamsFileMapper.search(params);

        CiamsFileDto.Search.Result result = CiamsFileDto.Search.Result.builder().page(page).list(rows).build();

        return result;
    }


    @Override
    @Transactional
    public List<CiamsFile> uploadEditorImages(CiamsFileDto.EditorImage.Upload params) throws Exception {
        List<CiamsFile> uploadFiles = new ArrayList<>();

        if (ValidUtil.empty(params.getFiles())) {
            throw new NullPointerException("NULL");
        }

        String filePath = FileUtil.getPathPrefix() + "/" + params.getTypeCode();

        if (!Files.exists(Paths.get(filePath))) {
            Files.createDirectories(Paths.get(filePath));
        }

        for (MultipartFile file : params.getFiles()) {
            String fileId = UUID.randomUUID().toString();
            String orgName = file.getOriginalFilename();
            String fileExt = StringUtil.getFileExt(orgName);
            String newName = fileId + "." + fileExt;

            /**
             * - ToDo : 타입, 사이즈 체크
             */
            try {
                file.transferTo(Paths.get(filePath + "/" + newName));
            } catch (IOException e) {
                log.error(e.getMessage());
                continue;
            }

            CiamsFile _file = CiamsFile.builder()
                    .id(fileId)
                    .orgName(orgName)
                    .newName(newName)
                    .size(file.getSize())
                    .ext(StringUtil.getFileExt(orgName))
                    .path("/" + params.getTypeCode())
                    .typeCode(params.getTypeCode())
                    .linkId(params.getLinkId())
                    .build();

            uploadFiles.add(_file);
        }

        ciamsFileMapper.insertFiles(uploadFiles);

        return uploadFiles;
    }
}
