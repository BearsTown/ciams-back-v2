//package com.uitgis.ciams.controller.basic.loc.industry;
//
//import com.uitgis.ciams.dto.basic.loc.industry.DensityDto;
//import com.uitgis.ciams.service.basic.loc.industry.DensityService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/basic/loc/industry-analysis")
//@RestController
//public class DensityController {
//    private final DensityService densityService;
//
//
//    /**
//     * 사업체 밀도변화 정보
//     *
//     * @param type
//     * @return
//     */
//    @GetMapping("/density/{type}")
//    public ResponseEntity<DensityDto.DensityInfo> getDensityInfo(@PathVariable("type") String type) {
//        DensityDto.DensityInfo result = densityService.getDensityInfo(type);
//        return ResponseEntity.ok(result);
//    }
//}
