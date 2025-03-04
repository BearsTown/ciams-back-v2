package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.CiamsIncentiveDetailsDto;
import com.uitgis.ciams.dto.CiamsIncentiveDto;
import com.uitgis.ciams.dto.IncentiveCalculateDto;
import com.uitgis.ciams.dto.IncentiveCheckListDto;
import com.uitgis.ciams.enums.ActionTypeEnum;
import com.uitgis.ciams.enums.MenuEnum;
import com.uitgis.ciams.exception.CustomException;
import com.uitgis.ciams.model.CiamsFile;
import com.uitgis.ciams.model.CiamsIncentiveCheckList;
import com.uitgis.ciams.service.CiamsCommonService;
import com.uitgis.ciams.service.CiamsFileService;
import com.uitgis.ciams.service.CiamsIncentiveService;
import com.uitgis.ciams.util.ValidUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/api/v1/incentive")
@RestController
@RequiredArgsConstructor
public class CiamsIncentiveController {
    private final CiamsFileService ciamsFileService;
    private final CiamsIncentiveService ciamsIncentiveService;
    private final CiamsCommonService ciamsCommonService;

    /**
     * 인센티브 검색 결과 조회
     */
    @GetMapping("/list")
    public ResponseEntity<?> getIncentiveList(CiamsIncentiveDto.Search.Find.Params params) {
    	Map<String, Object>  result = ciamsIncentiveService.getIncentiveList(params);
    	return ResponseEntity.ok(result);
    }

    /**
     * 인센티브 대상지 등록
     */
    @GetMapping("/targetland")
    public ResponseEntity<CiamsIncentiveDto.TargetLand.Find.Result> getTargetLand(CiamsIncentiveDto.TargetLand.Find.Params params) {
    	CiamsIncentiveDto.TargetLand.Find.Result result = ciamsIncentiveService.selectTargetLand(params);
		return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
	public ResponseEntity<?> addIncentive(@Param("params") @RequestBody CiamsIncentiveDto.Add params) throws Exception {
    	int count = 0;
    	count = ciamsIncentiveService.insertIncentive(params);
    	ciamsCommonService.log(MenuEnum.INCENTIVE, ActionTypeEnum.ADD, params.getIncentiveId());
		return ResponseEntity.ok(count > 0 ? true : false);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateIncentive(@RequestBody CiamsIncentiveDto.Add params) throws Exception {
    	int count = 0;
    	count = ciamsIncentiveService.updateIncentive(params);
    	ciamsCommonService.log(MenuEnum.INCENTIVE, ActionTypeEnum.UPDATE, params.getIncentiveId());
    	return ResponseEntity.ok(count > 0 ? true : false);
    }

    @DeleteMapping("/delete/{incentiveId}")
    public ResponseEntity<?> deleteIncentive(@PathVariable String incentiveId) throws Exception {
    	int cnt = ciamsIncentiveService.deleteIncentive(incentiveId);
    	ciamsCommonService.log(MenuEnum.INCENTIVE_LOC, ActionTypeEnum.DELETE, incentiveId);
		return ResponseEntity.ok(cnt > 0 ? true : false);
    }

    @DeleteMapping("/loc/delete/{incentiveLocId}")
    public ResponseEntity<?> deleteIncentiveLoc(@PathVariable String incentiveLocId) throws Exception {
    	int cnt = ciamsIncentiveService.deleteIncentiveLoc(incentiveLocId);
    	ciamsCommonService.log(MenuEnum.INCENTIVE_LOC, ActionTypeEnum.DELETE, incentiveLocId);
		return ResponseEntity.ok(cnt > 0 ? true : false);
    }

    /**
     * 인센티브 현황 상세정보 개요
     */
    @GetMapping("/overview")
    public ResponseEntity<CiamsIncentiveDetailsDto.Overview.Find.Result> getIncentiveOverview(CiamsIncentiveDetailsDto.Overview.Find.Params params) {
        CiamsIncentiveDetailsDto.Overview.Find.Result result = ciamsIncentiveService.getIncentiveOverview(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 인센티브 체크리스트
     */
    @GetMapping("/checklist/list")
    public ResponseEntity<List<IncentiveCheckListDto.Row>> getIncentiveCheckList(IncentiveCheckListDto.Find.Params params) {
        List<IncentiveCheckListDto.Row> result = ciamsIncentiveService.selectIncentiveCheckList(params);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/checklist/add")
    public ResponseEntity<String> addIncentiveCheckList(@RequestBody IncentiveCheckListDto.Insert data) {
        String result = ciamsIncentiveService.insertIncentiveCheckList(data);

        ciamsCommonService.log(MenuEnum.INCENTIVE_CHECKLIST, ActionTypeEnum.ADD, data.getIncentiveId());

        return ResponseEntity.ok(result);
    }

    @PostMapping("/checklist/update")
    public ResponseEntity<String> updateIncentiveCheckList(@RequestBody IncentiveCheckListDto.Insert data) {
        String result = ciamsIncentiveService.updateIncentiveCheckList(data);

        ciamsCommonService.log(MenuEnum.INCENTIVE_CHECKLIST, ActionTypeEnum.UPDATE, data.getId());

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/checklist/{id}/delete")
    public ResponseEntity<?> deleteIncentiveCheckList(@PathVariable String id) {
        int result = ciamsIncentiveService.deleteIncentiveCheckListById(id);

        ciamsCommonService.log(MenuEnum.INCENTIVE_CHECKLIST, ActionTypeEnum.DELETE, id);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/checklist/{id}/get")
    public ResponseEntity<?> getIncentiveCheckListById(@PathVariable String id) {
        CiamsIncentiveCheckList result = ciamsIncentiveService.selectIncentiveCheckListById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/checklist/{id}/print")
    public ResponseEntity<?> incentiveChecklistPrint(@PathVariable String id) {
        try {
            CiamsFile file = ciamsIncentiveService.incentiveCheckListPrint(id);

            ciamsCommonService.log(MenuEnum.INCENTIVE, ActionTypeEnum.PRINT, id);

            return getFile(file.getPath(), file.getOrgName());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.ok("error");
        }
    }

    @PostMapping("/checklist/file")
    public ResponseEntity<?> uploadFiles(CiamsIncentiveDetailsDto.IncentiveFile.Insert params) throws CustomException {
        int result = 0;

        if (ValidUtil.notEmpty(params.getFiles())) {
            try {
                ciamsIncentiveService.uploadCheckList(params);
            } catch (Exception e) {
                throw new CustomException("인센티브 엑셀 파일 등록에 실패했습니다.");
            }
        }

        return ResponseEntity.ok(result);
    }


    /**
     * 인센티브 정보
     */
    @GetMapping("/areasubject/list")
    public ResponseEntity<List<CiamsIncentiveDetailsDto.AreaSubject.Find.Result>> getIncentiveLoc(CiamsIncentiveDetailsDto.AreaSubject.Find.Params params) {
    	List<CiamsIncentiveDetailsDto.AreaSubject.Find.Result> result = ciamsIncentiveService.selectIncentiveLoc(params);
    	return ResponseEntity.ok(result);
    }


    @GetMapping("/info/list")
    public ResponseEntity<List<CiamsIncentiveDetailsDto.IncentiveInfo.Find.Result>> getIncentiveCalc(CiamsIncentiveDetailsDto.IncentiveInfo.Find.Params params) {
        List<CiamsIncentiveDetailsDto.IncentiveInfo.Find.Result> result = ciamsIncentiveService.selectIncentiveCalc(params);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/calculate/info")
    public ResponseEntity<?> getIncentiveCalculateInfo(IncentiveCalculateDto.Find.Params.Info params) {
        IncentiveCalculateDto.IncentiveInfo result = ciamsIncentiveService.selectIncentiveCalculateInfo(params);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/incen/get/{id}")
    public ResponseEntity<?> details(@PathVariable String id) {
        IncentiveCalculateDto.Details result = ciamsIncentiveService.selectIncentiveCalculateDetails(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/incen/groupNo")
    public ResponseEntity<?> selectGroupByNo() {
        List<String> result = ciamsIncentiveService.selectGroupByNo();
        return ResponseEntity.ok(result);
    }


    @PostMapping("/file/add")
    public ResponseEntity<?> addFiles(CiamsIncentiveDetailsDto.IncentiveFile.Insert params) throws Exception {
        int result = 0;

        if (ValidUtil.notEmpty(params.getFiles())) {
            try {
                result = ciamsFileService.uploadFiles(params.getFiles(), "incentive", params.getIncentiveId());

                ciamsCommonService.log(MenuEnum.FILE, ActionTypeEnum.ADD, params.getIncentiveId());

            } catch (Exception e) {
                throw new CustomException("파일 등록에 실패했습니다.");
            }
        }

        return ResponseEntity.ok(result);
    }

    /**
     *
     */
    @PostMapping("/record/{calcId}/info")
    public ResponseEntity<CiamsIncentiveDetailsDto.IncentiveInfo.Find.Result> getIncentiveCalculateRecord(@PathVariable String calcId) {
        CiamsIncentiveDetailsDto.IncentiveInfo.Find.Result result = ciamsIncentiveService.selectIncentiveCalculateRecord(calcId);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/record/add")
    public ResponseEntity<String> addIncentiveCalculate(@RequestBody IncentiveCalculateDto.Insert data) {
        String result = ciamsIncentiveService.insertIncentiveCalculate(data);

        ciamsCommonService.log(MenuEnum.INCENTIVE_RECORD, ActionTypeEnum.ADD, data.getIncentiveId());

        return ResponseEntity.ok(result);
    }

    @PostMapping("/record/update")
    public ResponseEntity<String> updateIncentiveCalculate(@RequestBody IncentiveCalculateDto.Insert data) {
        String result = ciamsIncentiveService.updateIncentiveCalculate(data);

        ciamsCommonService.log(MenuEnum.INCENTIVE_RECORD, ActionTypeEnum.UPDATE, data.getIncentiveId());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/record/{calcId}/state/examine")
    public ResponseEntity<?> updateIncentiveStateExamine(@PathVariable String calcId) {
        int result = ciamsIncentiveService.updateIncentiveStateExamine(calcId);

        ciamsCommonService.log(MenuEnum.INCENTIVE_RECORD, ActionTypeEnum.UPDATE, calcId);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/record/{calcId}/state/finaliz")
    public ResponseEntity<?> updateIncentiveStateFinaliz(@PathVariable String calcId) {
        int result = ciamsIncentiveService.updateIncentiveStateFinaliz(calcId);

        ciamsCommonService.log(MenuEnum.INCENTIVE_RECORD, ActionTypeEnum.UPDATE, calcId);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/record/{calcId}/delete")
    public ResponseEntity<?> deleteIncentiveRecord(@PathVariable String calcId) {
        int result = ciamsIncentiveService.deleteIncentiveCalcById(calcId);

        ciamsCommonService.log(MenuEnum.INCENTIVE_RECORD, ActionTypeEnum.DELETE, calcId);

        return ResponseEntity.ok(result);
    }


    @GetMapping("/record/{calcId}/print")
    public ResponseEntity<?> incentivePrint(@PathVariable String calcId) {
        try {
            CiamsFile file = ciamsIncentiveService.incentivePrint(calcId);

            ciamsCommonService.log(MenuEnum.INCENTIVE, ActionTypeEnum.PRINT, calcId);

            return getFile(file.getPath(), file.getOrgName());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.ok("error");
        }
    }

    private ResponseEntity<?> getFile(String filePath, String filename) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        File downloadFile = new File(filePath);

        if (downloadFile.exists()) {
            String downloadPath = downloadFile.getAbsolutePath();
            log.info("Download Path = {}", downloadFile.toURI());
            try {
                InputStreamResource resource = new InputStreamResource(Files.newInputStream(Paths.get(downloadPath), StandardOpenOption.READ));

                String fileName = URLEncoder.encode(filename, "UTF-8");

                return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/download; charset=utf-8")).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"").body(resource);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("file error.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("file does not exist.");
        }
    }

    @GetMapping("/monitoring/area")
    public ResponseEntity<?> getChartGroupAreaUse(@RequestParam(value="pnu", required = false) List<String> pnu) {
    	List<CiamsIncentiveDetailsDto.chartGroupData> dataResult = ciamsIncentiveService.getChartGroupAreaUse(pnu);
    	List<CiamsIncentiveDetailsDto.chartGeometryData> geoResult = ciamsIncentiveService.selectGeometrys(pnu);

        Map<String, Object> result = new HashMap<>();
    	result.put("data", dataResult);
    	result.put("geoData", geoResult);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/monitoring/jimok")
    public ResponseEntity<?> getChartGroupJimok(@RequestParam(value="pnu", required = false) List<String> pnu) {
    	List<CiamsIncentiveDetailsDto.chartGroupData> dataResult = ciamsIncentiveService.getChartGroupJimok(pnu);
    	List<CiamsIncentiveDetailsDto.chartGeometryData> geoResult = ciamsIncentiveService.selectGeometrys(pnu);

        Map<String, Object> result = new HashMap<>();
    	result.put("data", dataResult);
    	result.put("geoData", geoResult);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/monitoring/year")
    public ResponseEntity<?> getChartGroupYear(@RequestParam(value="pnu", required = false) List<String> pnu) {
    	List<CiamsIncentiveDetailsDto.chartGroupData> dataResult = ciamsIncentiveService.getChartGroupYear(pnu);

        Map<String, Object> result = new HashMap<>();
    	result.put("data", dataResult);

        return ResponseEntity.ok(result);
    }


    /**
     *
     */
    @GetMapping("/{pnu}/pnu")
    public ResponseEntity<?> getPnuIncentive(@PathVariable String pnu) {
        List<CiamsIncentiveDto.PNUIncentive> result = ciamsIncentiveService.selectPNUIncentive(pnu);
        return ResponseEntity.ok(result);
    }
}
