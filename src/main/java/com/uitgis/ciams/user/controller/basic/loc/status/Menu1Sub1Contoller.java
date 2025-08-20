//package com.uitgis.ciams.controller.basic.loc;
//
//import com.uitgis.ciams.dto.basic.loc.industry.TechLQDto;
//import com.uitgis.ciams.service.basic.loc.industry.TechLQService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.annotations.Param;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@Slf4j
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/menu-1/sub-1")
//@RestController
//public class Menu1Sub1Contoller {
//
//    private final TechLQService techLQService;
//
//    @PostMapping("/tech")
//    public ResponseEntity<TechLQDto.HighTech.Find.Result> getHighTech(@Param("params") @RequestBody
//                                                                               TechLQDto.HighTech.Find.Params params) {
//        TechLQDto.HighTech.Find.Result result = techLQService.getHighTech(params);
//        return ResponseEntity.ok(result);
//    }
//
//    @PostMapping("/tech/lq")
//    public ResponseEntity<List<TechLQDto.TechLQ.Find.Result>> getTechLQs(@Param("params") @RequestBody
//                                                                           TechLQDto.TechLQ.Find.Params params) {
//        List<TechLQDto.TechLQ.Find.Result> result = techLQService.getTechLQs(params);
//        return ResponseEntity.ok(result);
//    }
//}
