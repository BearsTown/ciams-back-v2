//package com.uitgis.ciams.controller.basic.loc.industry;
//
//import com.uitgis.ciams.dto.basic.loc.industry.ManufacturingDto;
//import com.uitgis.ciams.service.basic.loc.industry.ManufacturingService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/basic/loc/industry-analysis")
//@RestController
//public class ManufacturingController {
//    private final ManufacturingService manufacturingService;
//
//
//    /**
//     * 제조업별 사업체수/종사자수 정보
//     *
//     * @param params
//     * @return
//     */
//    @PostMapping("/mfg")
//    public ResponseEntity<ManufacturingDto.Info> getManufacturingInfo(@RequestBody ManufacturingDto.Find.Params params) {
//        ManufacturingDto.Info result = manufacturingService.getManufacturingInfo(params);
//        return ResponseEntity.ok(result);
//    }
//}
