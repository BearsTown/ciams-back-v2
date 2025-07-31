package com.uitgis.ciams.controller.basic.loc;

import com.uitgis.ciams.dto.basic.loc.status.DensityDto;
import com.uitgis.ciams.dto.basic.loc.status.ManufacturingDto;
import com.uitgis.ciams.dto.basic.loc.status.ScopeDto;
import com.uitgis.ciams.dto.basic.loc.status.IndustrialDto;
import com.uitgis.ciams.dto.basic.loc.status.TechLQDto;
import com.uitgis.ciams.service.basic.loc.status.DensityService;
import com.uitgis.ciams.service.basic.loc.status.ManufacturingService;
import com.uitgis.ciams.service.basic.loc.status.ScopeService;
import com.uitgis.ciams.service.basic.loc.status.IndustrialService;
import com.uitgis.ciams.service.basic.loc.status.TechLQService;
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
@RequestMapping("/api/v1/basic/loc/ind-status")
@RestController
public class IndustryStatusController {
    private final ScopeService scopeService;
    private final DensityService densityService;
    private final IndustrialService industrialService;
    private final ManufacturingService manufacturingService;
    private final TechLQService techLQService;


    /**
     * 산업의 범위 목록
     *
     * @return
     */
    @GetMapping("/scope")
    public ResponseEntity<List<ScopeDto.Data>> getScopes() {
        List<ScopeDto.Data> result = scopeService.getScopes();
        return ResponseEntity.ok(result);
    }


    /**
     * 사업체 밀도변화 정보
     *
     * @param type
     * @return
     */
    @GetMapping("/density/{type}/info")
    public ResponseEntity<DensityDto.DensityInfo> getDensityInfo(@PathVariable("type") String type) {
        DensityDto.DensityInfo result = densityService.getDensityInfo(type);
        return ResponseEntity.ok(result);
    }


    /**
     * 산업별 사업체수/종사자수 정보
     *
     * @param params
     * @return
     */
    @PostMapping("/industrial")
    public ResponseEntity<IndustrialDto.Info> getIndustrialInfo(@RequestBody IndustrialDto.Find.Params params) {
        IndustrialDto.Info result = industrialService.getIndustrialInfo(params);
        return ResponseEntity.ok(result);
    }


    /**
     * 제조업별 사업체수/종사자수 정보
     *
     * @param params
     * @return
     */
    @PostMapping("/mfg/mfg-info")
    public ResponseEntity<ManufacturingDto.Info> getManufacturingInfo(@RequestBody ManufacturingDto.Find.Params params) {
        ManufacturingDto.Info result = manufacturingService.getManufacturingInfo(params);
        return ResponseEntity.ok(result);
    }


    /**
     * 제조업 유형별 현황 정보
     *
     * @return
     */
    @GetMapping("/mfg/cls-info")
    public ResponseEntity<ManufacturingDto.CategoryStatus.Info> getCategoryStatusInfo() {
        ManufacturingDto.CategoryStatus.Info result = manufacturingService.getCategoryStatusInfo();
        return ResponseEntity.ok(result);
    }


    /**
     * 집적도 높은 기술
     *
     * @param params
     * @return
     */
    @PostMapping("/tech")
    public ResponseEntity<TechLQDto.HighTech.Find.Result> getHighTech(@RequestBody TechLQDto.HighTech.Find.Params params) {
        TechLQDto.HighTech.Find.Result result = techLQService.getHighTech(params);
        return ResponseEntity.ok(result);
    }


    /**
     * 기술업종목록
     *
     * @param params
     * @return
     */
    @PostMapping("/tech/lq")
    public ResponseEntity<List<TechLQDto.TechLQ.Find.Result>> getTechLQs(@RequestBody TechLQDto.TechLQ.Find.Params params) {
        List<TechLQDto.TechLQ.Find.Result> result = techLQService.getTechLQs(params);
        return ResponseEntity.ok(result);
    }

}
