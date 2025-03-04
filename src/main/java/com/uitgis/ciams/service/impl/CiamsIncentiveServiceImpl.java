package com.uitgis.ciams.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uitgis.ciams.dto.CiamsConfigDto;
import com.uitgis.ciams.dto.CiamsIncentiveDetailsDto;
import com.uitgis.ciams.dto.CiamsIncentiveDto;
import com.uitgis.ciams.dto.IncentiveCalculateDto;
import com.uitgis.ciams.dto.IncentiveCheckListDto;
import com.uitgis.ciams.exception.CustomException;
import com.uitgis.ciams.mapper.CiamsIncentiveMapper;
import com.uitgis.ciams.model.CiamsFile;
import com.uitgis.ciams.model.CiamsIncentiveCheckList;
import com.uitgis.ciams.service.CiamsConfigService;
import com.uitgis.ciams.service.CiamsIncentiveService;
import com.uitgis.ciams.util.FileUtil;
import com.uitgis.ciams.util.JsonUtil;
import com.uitgis.ciams.util.ValidUtil;
import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.reader.HWPReader;
import kr.dogfoot.hwplib.tool.objectfinder.FieldFinder;
import kr.dogfoot.hwplib.writer.HWPWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsIncentiveServiceImpl implements CiamsIncentiveService {
    @Value("${file.path.calc-templates}")
    private String calcTemplates;

    private final CiamsConfigService ciamsConfigService;
    private final CiamsIncentiveMapper ciamsIncentiveMapper;

    @Override
    public Map<String, Object> getIncentiveList(CiamsIncentiveDto.Search.Find.Params params) {
		int pageNo = params.getPageNo() != null ? (params.getPageNo() - 1) * 5 : 0;
		params.setPageNo(pageNo);
    	if(params.getMainJibun() != null && !params.getMainJibun().equals("")) {
    		String mainJibun = params.getMainJibun();
    		mainJibun = String.format("%04d", Integer.parseInt(mainJibun));
    		params.setMainJibun(mainJibun);
    	}
    	if(params.getSubJibun() != null && !params.getSubJibun().equals("")) {
    		String subJibun = params.getSubJibun();
    		subJibun = String.format("%04d", Integer.parseInt(subJibun));
    		params.setSubJibun(subJibun);
    	}
    	int total = 0;
    	if(params.getPaged() == null || params.getPaged()) {
    		total = ciamsIncentiveMapper.getIncentiveTotal(params);
    	}
    	List<CiamsIncentiveDto.Search.Find.IncenSearchList> list = ciamsIncentiveMapper.getIncentiveList(params);
    	Map<String, Object> resultMap = new HashMap<>();
    	resultMap.put("total", total);
    	resultMap.put("list", list);
    	return resultMap;
    }

    @Override
    public CiamsIncentiveDto.TargetLand.Find.Result selectTargetLand(CiamsIncentiveDto.TargetLand.Find.Params params) {
    	return ciamsIncentiveMapper.selectTargetLand(params);
    }

    @Override
    @Transactional
    public int insertIncentive(com.uitgis.ciams.dto.CiamsIncentiveDto.Add params) {
    	String incentiveId = UUID.randomUUID().toString();
    	params.setIncentiveId(incentiveId);
    	int result = ciamsIncentiveMapper.insertIncentive(params);
    	List<com.uitgis.ciams.dto.CiamsIncentiveDto.Loc> locs = params.getIncentiveLocs();
    	for (com.uitgis.ciams.dto.CiamsIncentiveDto.Loc incentiveLoc : locs) {
    		incentiveLoc.setIncentiveId(params.getIncentiveId());
    		String incentiveLocsId = UUID.randomUUID().toString();
    		incentiveLoc.setIncentiveLocId(incentiveLocsId);
    		ciamsIncentiveMapper.insertIncentiveLocs(incentiveLoc);
    	}
    	return result;
    }

    @Override
    @Transactional
    public int updateIncentive(com.uitgis.ciams.dto.CiamsIncentiveDto.Add params) {
    	List<com.uitgis.ciams.dto.CiamsIncentiveDto.Loc> locs = params.getIncentiveLocs();
    	for (com.uitgis.ciams.dto.CiamsIncentiveDto.Loc incentiveLoc : locs) {
    		if (incentiveLoc.getIncentiveLocId() == "") {
    			incentiveLoc.setIncentiveId(params.getIncentiveId());
    			String incentiveLocsId = UUID.randomUUID().toString();
    			incentiveLoc.setIncentiveLocId(incentiveLocsId);
    			ciamsIncentiveMapper.insertIncentiveLocs(incentiveLoc);
    		} else {
    			ciamsIncentiveMapper.updateIncentiveLocs(incentiveLoc);
    		}
    	}
    	return ciamsIncentiveMapper.updateIncentive(params);
    }

    @Override
    @Transactional
    public int deleteIncentive(String incentiveId) {
    	return ciamsIncentiveMapper.deleteIncentive(incentiveId);
    }

    @Override
    @Transactional
    public int deleteIncentiveLoc(String incentiveLocId) {
    	return ciamsIncentiveMapper.deleteIncentiveLoc(incentiveLocId);
    }



    @Override
    public CiamsIncentiveDetailsDto.Overview.Find.Result getIncentiveOverview(CiamsIncentiveDetailsDto.Overview.Find.Params params) {
        CiamsIncentiveDetailsDto.Overview.Find.Result result = ciamsIncentiveMapper.selectIncentiveOverview(params);
        if ( result != null ) {
            result.setAreaUseCds(ciamsIncentiveMapper.selectAreaUseCdList(params));
        }
        return result;
    }

    @Override
    public CiamsIncentiveCheckList selectIncentiveCheckListById(String id) {
        CiamsIncentiveCheckList data = ciamsIncentiveMapper.selectIncentiveCheckListById(id);
        if (data.getContents() != null) {
            JSONParser parser = new JSONParser();

            try {
                data.setContents((JSONObject) parser.parse(data.getContents().toString()));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return data;
    }

    /**
     *
     */
    @Override
    public List<IncentiveCheckListDto.Row> selectIncentiveCheckList(IncentiveCheckListDto.Find.Params params) {
        return ciamsIncentiveMapper.selectIncentiveCheckList(params);
    }

    @Override
    public String insertIncentiveCheckList(IncentiveCheckListDto.Insert data) {
        int result = ciamsIncentiveMapper.insertIncentiveCheckList(data);
        return data.getId();
    }

    @Override
    public String updateIncentiveCheckList(IncentiveCheckListDto.Insert data) {
        int result = ciamsIncentiveMapper.updateIncentiveCheckList(data);
        return data.getId();
    }

    @Override
    public int deleteIncentiveCheckListById(String id) {
        IncentiveCheckListDto.Delete params = IncentiveCheckListDto.Delete.builder()
                .id(id)
                .build();
        return ciamsIncentiveMapper.deleteIncentiveCheckListById(params);
    }

    @Override
    public CiamsFile incentiveCheckListPrint(String id) throws Exception {
        CiamsIncentiveCheckList result = selectIncentiveCheckListById(id);

        String templateFileName = "checklist.hwp";
        CiamsConfigDto.WithFile ciamsConfig = new CiamsConfigDto.WithFile();

        String _path = calcTemplates;
        String _fileName = templateFileName;

        CiamsFile templateFile = CiamsFile
                .builder()
                .path(_path)
                .newName(_fileName)
                .build();

        File tlFile = FileUtil.getFile(templateFile);
        if (!tlFile.exists()) {
            File Folder = new File(FileUtil.getPathPrefix() + calcTemplates);
            if (!Folder.exists()) {
                Folder.mkdir();
            }

            ClassPathResource resource = new ClassPathResource("calc-templates" + File.separator + templateFileName);
            Files.copy(resource.getFile().toPath(), tlFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        HWPFile hwpFile = HWPReader.fromFile(tlFile);

        String resultPath = FileUtil.getPathPrefix() + FileUtil.getFileTempPath() + "/result";

        if (!Files.exists(Paths.get(resultPath))) {
            Files.createDirectories(Paths.get(resultPath));
        }

        if (result == null) {
            throw new NotFoundException("Not Files");
        }

        if (result.getContents() != null) {
            JSONParser parser = new JSONParser();
            List<String> subs = new ArrayList<>(Arrays.asList("location", "area", "target", "build"));
            JSONObject json = ((JSONObject) parser.parse(result.getContents().toString()));

            subs.forEach(i -> {
                try {
                    FieldFinder.setClickHereText(hwpFile, i, new ArrayList<>(Arrays.asList(json.get(i).toString())));
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            });

            if (json.get("items") != null) {
                JSONObject items = (JSONObject) json.get("items");
                items.forEach((k, v) -> {
                    if (v == null) return;

                    JSONObject attrs = (JSONObject) v;
                    attrs.forEach((k2, v2) -> {
                        if (v2 instanceof Boolean) {
                            String filed = k + "_" + k2;
                            String value = "";

                            if ((Boolean) v2) {
                                filed += "_Y";
                                value = "O";
                            } else {
                                filed += "_N";
                                value = "X";
                            }
                            try {
                                FieldFinder.setClickHereText(hwpFile, filed, new ArrayList<>(Arrays.asList(value)));
                            } catch (Exception e) {
                                log.error(e.getMessage());
                            }
                        }
                    });
                });
            }
        }

        String newName = UUID.randomUUID().toString();
        Date createDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(result.getCreateDate());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String time = dateFormat.format(createDate);
        String fileName = "인센티브체크리스트_" + result.getTitle() + "_" + time + ".hwp";

        CiamsFile createHwpFile = CiamsFile
                .builder()
                .path(resultPath + File.separator + newName)
                .orgName(fileName)
                .newName(newName)
                .build();

        HWPWriter.toFile(hwpFile, createHwpFile.getPath());

        return createHwpFile;
//        return null;
    }

    @Override
    public void uploadCheckList(CiamsIncentiveDetailsDto.IncentiveFile.Insert params) throws CustomException {
        if (ValidUtil.empty(params.getFiles())) return;

        for (MultipartFile file : params.getFiles()) {
            Workbook workbook = null;
            try {
                workbook = new XSSFWorkbook(file.getInputStream());
            } catch (IOException e) {
                continue;
            }

            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                Sheet sheet = workbook.getSheetAt(sheetNum);

                if (ValidUtil.empty(sheet)) throw new CustomException("엑셀 파일에 시트가 없습니다.");

                Map<String, Object> checkListMap = new HashMap<>();
                checkListMap.put("title", sheet.getSheetName());
                checkListMap.put("location", getItemValue(sheet.getRow(6)));
                checkListMap.put("area", getItemValue(sheet.getRow(7)));
                checkListMap.put("target", getItemValue(sheet.getRow(8)));
                checkListMap.put("build", getItemValue(sheet.getRow(9)));

                List<Integer> itemRowCountList = new ArrayList<>(Arrays.asList(5, 2, 1, 1, 2, 3, 1, 3, 7, 2, 1, 2, 3));

                int startRowNum = 13;
                Map<String, Map<Character, Boolean>> itemsMap = new LinkedHashMap<>();

                for (int i = 1; i < itemRowCountList.size() + 1; i++) {
                    Map<Character, Boolean> itemValue = new LinkedHashMap<>();
                    String itemKey = "ITEM_" + i;
                    for (int j = 0; j < itemRowCountList.get(i - 1); j++) {
                        itemValue.put((char) (j + 65), getItemState(sheet.getRow(startRowNum)));
                        itemsMap.put(itemKey, itemValue);
                        startRowNum++;
                    }
                }

                checkListMap.put("items", itemsMap);

                JSONObject jsonObject = new JSONObject(checkListMap);

                IncentiveCheckListDto.Insert data = IncentiveCheckListDto.Insert
                        .builder()
                        .incentiveId(params.getIncentiveId())
                        .title(checkListMap.get("title").toString())
                        .contents(jsonObject.toJSONString())
                        .build();

                insertIncentiveCheckList(data);
            }

            try {
                workbook.close();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    @Override
    public List<CiamsIncentiveDetailsDto.AreaSubject.Find.Result> selectIncentiveLoc(CiamsIncentiveDetailsDto.AreaSubject.Find.Params params) {
        return ciamsIncentiveMapper.selectIncentiveLoc(params);
    }

    @Override
    public List<CiamsIncentiveDetailsDto.IncentiveInfo.Find.Result> selectIncentiveCalc(CiamsIncentiveDetailsDto.IncentiveInfo.Find.Params params) {
        List<CiamsIncentiveDetailsDto.IncentiveInfo.Find.Result> rows = ciamsIncentiveMapper.selectIncentiveCalc(params);

        if (!rows.isEmpty()) {
            rows.forEach(row -> {
                if (row.getIncentives() != null) {
                    JSONParser parser = new JSONParser();

                    // - To DO : TypeHandler
                    try {
                        row.setIncentives((JSONObject) parser.parse(row.getIncentives().toString()));
                    } catch (Exception e) {
                    	log.error(e.getMessage());
                    }
                }
                if (row.getSummary() != null) {
                    JSONParser parser = new JSONParser();

                    // - To DO : TypeHandler
                    try {
                        row.setSummary((JSONObject) parser.parse(row.getSummary().toString()));
                    } catch (Exception e) {
                    	log.error(e.getMessage());
                    }
                }
            });
        }

        return rows;
    }


    @Override
    public int updateIncentiveStateExamine(String calcId) {
        IncentiveCalculateDto.Update params = IncentiveCalculateDto.Update.builder()
                .state(0)
                .calcId(calcId)
                .build();
        return ciamsIncentiveMapper.updateIncentiveCalculateStateById(params);
    }

    @Override
    public int updateIncentiveStateFinaliz(String calcId) {
        IncentiveCalculateDto.Update params = IncentiveCalculateDto.Update.builder()
                .state(1)
                .calcId(calcId)
                .build();
        ciamsIncentiveMapper.updateStateDisableAllById(calcId);
        return ciamsIncentiveMapper.updateIncentiveCalculateStateById(params);
    }

    @Override
    public int deleteIncentiveCalcById(String calcId) {
        IncentiveCalculateDto.Delete params = IncentiveCalculateDto.Delete.builder()
                .calcId(calcId)
                .build();
        return ciamsIncentiveMapper.deleteIncentiveCalcById(params);
    }


    @Override
    public IncentiveCalculateDto.Details selectIncentiveCalculateDetails(String incentiveId) {
        return null;
    }

    @Override
    public CiamsIncentiveDetailsDto.IncentiveInfo.Find.Result selectIncentiveCalculateRecord(String calcId) {
        return ciamsIncentiveMapper.selectIncentiveCalcById(calcId);
    }

    @Override
    @Transactional(readOnly = true)
    public IncentiveCalculateDto.IncentiveInfo selectIncentiveCalculateInfo(IncentiveCalculateDto.Find.Params.Info info) {
        IncentiveCalculateDto.AreaIncentive areaIncentive = ciamsIncentiveMapper.selectIncentiveCalculateAreas(info);

        if (areaIncentive == null) {
            return null;
        }

        IncentiveCalculateDto.Find.Params.Props propsParams = IncentiveCalculateDto.Find.Params.Props
                .builder()
                .areaUseCd(areaIncentive.getAreaUseCd())
                .incentiveNtfcNo(areaIncentive.getIncentiveNtfcNo())
                .build();

        List<IncentiveCalculateDto.Props> props = ciamsIncentiveMapper.selectIncentiveProps(propsParams);


        if (!areaIncentive.getIncentiveItems().isEmpty()) {
            areaIncentive.getIncentiveItems().forEach(incenItem -> {
                JSONParser parser = new JSONParser();

                // - To DO : TypeHandler
                try {
                    if (incenItem.getFormulaBcrParams() != null) {
                        Object bcrObj = parser.parse(incenItem.getFormulaBcrParams().toString());
                        incenItem.setFormulaBcrParams((JSONObject) bcrObj);
                    }
                    if (incenItem.getFormulaFarParams() != null) {
                        Object farObj = parser.parse(incenItem.getFormulaFarParams().toString());
                        incenItem.setFormulaFarParams((JSONObject) farObj);
                    }
                } catch (Exception e) {
                	log.error(e.getMessage());
                }
            });
        }

        IncentiveCalculateDto.IncentiveInfo result = IncentiveCalculateDto.IncentiveInfo
                .builder()
                .props(props)
                .areaIncentive(areaIncentive)
                .build();

        return result;
    }


    @Override
    public String insertIncentiveCalculate(IncentiveCalculateDto.Insert data) {
        int result = ciamsIncentiveMapper.insertIncentiveCalculate(data);
        return data.getIncentiveId();
    }

    @Override
    public String updateIncentiveCalculate(IncentiveCalculateDto.Insert data) {
        int result = ciamsIncentiveMapper.updateIncentiveCalculate(data);
        return data.getIncentiveId();
    }


    @Override
    public List<String> selectGroupByNo() {
        return null;
    }

    @Override
    public CiamsFile incentivePrint(String calcId) throws Exception {
        CiamsIncentiveDetailsDto.IncentiveInfo.Find.Result result = ciamsIncentiveMapper.selectIncentiveCalcById(calcId);

        String templateFileName = "";
        boolean check = false;
        CiamsConfigDto.WithFile ciamsConfig = new CiamsConfigDto.WithFile();

        String confId = "";
        switch (result.getAreaUseCode()) {
            case "UQA430": {
                templateFileName = "UQA430.hwp";
                confId = "INCEN_HWP_A";
                break;
            }
            case "UQB100": {
                templateFileName = "UQB100.hwp";
                confId = "INCEN_HWP_B";
                break;
            }
            case "UQB100_C1011": {
                templateFileName = "UQB100_C1011.hwp";
                confId = "INCEN_HWP_B";
                break;
            }
            case "UQB200": {
                templateFileName = "UQB200.hwp";
                confId = "INCEN_HWP_C";
                break;
            }
            case "UQC001": {
                templateFileName = "UQC001.hwp";
                confId = "INCEN_HWP_D";
                break;
            }
        }

        try {
            ciamsConfig = ciamsConfigService.getConfById(confId)
                    .orElseThrow(() -> new CustomException("config info does not exist"));
        } catch (CustomException e) {
            check = true;
        }

        String _path = calcTemplates;
        String _fileName = templateFileName;

        check = true; // temp

        if (!check) {
            _path = "/" + ciamsConfig.getAttachFile().getTypeCode();
            _fileName = ciamsConfig.getAttachFile().getNewName();
        }

        CiamsFile templateFile = CiamsFile
                .builder()
                .path(_path)
                .newName(_fileName)
                .build();

        File tlFile = FileUtil.getFile(templateFile);
        if (!tlFile.exists()) {
            File Folder = new File(FileUtil.getPathPrefix() + calcTemplates);
            if (!Folder.exists()) {
                Folder.mkdir();
            }

            ClassPathResource resource = new ClassPathResource("calc-templates" + File.separator + templateFileName);
            Files.copy(resource.getFile().toPath(), tlFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        HWPFile hwpFile = HWPReader.fromFile(tlFile);

        String resultPath = FileUtil.getPathPrefix() + FileUtil.getFileTempPath() + "/result";

        if (!Files.exists(Paths.get(resultPath))) {
            Files.createDirectories(Paths.get(resultPath));
        }

        if (result == null && result.getSummary() == null) {
            throw new NotFoundException("Not Summary");
        }

        ObjectMapper objectMapper = JsonUtil.getObjectMapper();
        HashMap<String, Object> map = objectMapper.readValue(result.getSummary().toString(), HashMap.class);

        Iterator<String> keys = map.keySet().iterator();
        while (keys.hasNext()) {
            String strKey = keys.next();
            if ( map.get(strKey) == null ) continue;
            String strValue = map.get(strKey).toString();
            ArrayList<String> textList = new ArrayList<String>();
            textList.add(strValue);
            FieldFinder.setClickHereText(hwpFile, strKey, textList);
        }

        String newName = UUID.randomUUID().toString();
        Date createDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(result.getCreateDate());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String time = dateFormat.format(createDate);
        String fileName = "인센티브산정표_" + result.getAreaUseNm() + "_" + time + ".hwp";

        CiamsFile createHwpFile = CiamsFile
                .builder()
                .path(resultPath + File.separator + newName)
                .orgName(fileName)
                .newName(newName)
                .build();

        HWPWriter.toFile(hwpFile, createHwpFile.getPath());

        return createHwpFile;
    }

    /**
     * 모니터링 차트데이터 조회(인센티브현황)
     */
	@Override
	public List<CiamsIncentiveDetailsDto.chartGroupData> getChartGroupJimok(List<String> pnu) {
		return ciamsIncentiveMapper.selectGroupByJimok(pnu);
	}

	@Override
	public List<CiamsIncentiveDetailsDto.chartGroupData> getChartGroupAreaUse(List<String> pnu) {
		return ciamsIncentiveMapper.selectGroupByAreaUse(pnu);
	}

	@Override
	public List<CiamsIncentiveDetailsDto.chartGroupData> getChartGroupYear(List<String> pnu) {
		return ciamsIncentiveMapper.selectGroupByYear(pnu);
	}

	@Override
	public List<CiamsIncentiveDetailsDto.chartGeometryData> selectGeometrys(List<String> pnu) {
		return ciamsIncentiveMapper.selectGeometrys(pnu);
	}

    @Override
    public List<CiamsIncentiveDto.PNUIncentive> selectPNUIncentive(String pnu) {
        List<CiamsIncentiveDto.PNUIncentive> rows = ciamsIncentiveMapper.selectPNUIncentive(pnu);

        if (!rows.isEmpty()) {
            rows.forEach(row -> {
                if (row.getSummary() != null) {
                    JSONParser parser = new JSONParser();

                    try {
                        row.setSummary((JSONObject) parser.parse(row.getSummary().toString()));
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }
            });
        }

        return rows;
    }

    private boolean getItemState(Row row) {
        Cell cell_O = row.getCell(7);
        return cell_O != null && cell_O.getCellType() == CellType.STRING;
    }

    private String getItemValue(Row row) {
        if (row != null) {
            Cell cell = row.getCell(5);
            if (cell != null && cell.getCellType() == CellType.STRING) {
                return cell.getStringCellValue();
            }
        }
        return "";
    }
}
