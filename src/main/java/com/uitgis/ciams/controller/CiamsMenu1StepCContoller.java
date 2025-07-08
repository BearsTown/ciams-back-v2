package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.CiamsMenu1StepCDetailsDto;
import com.uitgis.ciams.service.CiamsMenu1StepCService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu-1/step-c")
@RestController
public class CiamsMenu1StepCContoller {

    private final CiamsMenu1StepCService ciamsMenu1StepCService;


    /**
     * 상세정보 개요
     */
    @GetMapping("/overview")
    public ResponseEntity<CiamsMenu1StepCDetailsDto.Overview.Find.Result> getIncentiveOverview(CiamsMenu1StepCDetailsDto.Overview.Find.Params params) {
        CiamsMenu1StepCDetailsDto.Overview.Find.Result result = ciamsMenu1StepCService.getOverView(params);
        return ResponseEntity.ok(result);
    }
}
