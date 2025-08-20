//package com.uitgis.ciams.controller.basic.loc.industry;
//
//import com.uitgis.ciams.dto.basic.loc.industry.SectorsDto;
//import com.uitgis.ciams.service.basic.loc.industry.SectorsService;
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
//public class SectorsController {
//    private final SectorsService sectorsService;
//
//
//    /**
//     * 산업별 사업체수/종사자수 정보
//     *
//     * @param params
//     * @return
//     */
//    @PostMapping("/sectors")
//    public ResponseEntity<SectorsDto.Info> getIndustrialInfo(@RequestBody SectorsDto.Find.Params params) {
//        SectorsDto.Info result = sectorsService.getIndustrialInfo(params);
//        return ResponseEntity.ok(result);
//    }
//}
