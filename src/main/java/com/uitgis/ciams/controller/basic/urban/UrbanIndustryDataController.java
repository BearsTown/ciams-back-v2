package com.uitgis.ciams.controller.basic.urban;

import com.uitgis.ciams.dto.basic.urban.CiamsBasicUrbanDto;
import com.uitgis.ciams.dto.basic.urban.UrbanIndustryDto;
import com.uitgis.ciams.model.basic.urban.CiamsDataGroup;
import com.uitgis.ciams.service.basic.urban.UrbanIndustryDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/basic/urban/data")
@RestController
public class UrbanIndustryDataController {
    private final UrbanIndustryDataService urbanIndustryDataService;


    /**
     * 도시공업지역 현황 데이터 그룹 목록
     *
     * @param parentId
     * @return
     */
    @GetMapping("/{id}/group")
    public ResponseEntity<List<CiamsDataGroup>> getDataGroups(@PathVariable("id") int parentId) {
        List<CiamsDataGroup> result = urbanIndustryDataService.getDataGroups(parentId);
        return ResponseEntity.ok(result);
    }


    /**
     * 도시공업지역 현황 데이터 메타데이터
     *
     * @param dataGroupId
     * @return
     */
    @GetMapping("/{id}/metadata")
    public ResponseEntity<UrbanIndustryDto.MetaData> getMetaData(@PathVariable("id") int dataGroupId) {
        UrbanIndustryDto.MetaData result = urbanIndustryDataService.getMetaData(dataGroupId);
        return ResponseEntity.ok(result);
    }


    /**
     * 도시공업지역 현황 데이터 정보
     *
     * @param params
     * @return
     */
    @PostMapping("/info")
    public ResponseEntity<CiamsBasicUrbanDto.Info.Find.Result> getDataInfo(@RequestBody CiamsBasicUrbanDto.Info.Find.Params params) {
        return ResponseEntity.ok(urbanIndustryDataService.getDataInfo(params));
    }
}
