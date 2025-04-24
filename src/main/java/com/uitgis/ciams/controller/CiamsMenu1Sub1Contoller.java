package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.CiamsTechLQDto;
import com.uitgis.ciams.service.CiamsMenu1Sub1Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu-1/sub-1")
@RestController
public class CiamsMenu1Sub1Contoller {

    private final CiamsMenu1Sub1Service ciamsMenu1Sub1Service;

    @PostMapping("/tech")
    public ResponseEntity<CiamsTechLQDto.HighTech.Find.Result> getHighTech(@Param("params") @RequestBody
                                                                               CiamsTechLQDto.HighTech.Find.Params params) {
        CiamsTechLQDto.HighTech.Find.Result result = ciamsMenu1Sub1Service.getHighTech(params);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/tech/lq")
    public ResponseEntity<List<CiamsTechLQDto.TechLQ.Find.Result>> getTechLQ(@Param("params") @RequestBody
                                                                           CiamsTechLQDto.TechLQ.Find.Params params) {
        List<CiamsTechLQDto.TechLQ.Find.Result> result = ciamsMenu1Sub1Service.getTechLQ(params);
        return ResponseEntity.ok(result);
    }
}
