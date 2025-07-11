package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.Menu1StepCDetailsDto;
import com.uitgis.ciams.service.Menu1StepCService;
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
public class Menu1StepCContoller {

    private final Menu1StepCService menu1StepCService;


    /**
     * 상세정보 개요
     */
    @GetMapping("/overview")
    public ResponseEntity<Menu1StepCDetailsDto.Overview.Find.Result> getIncentiveOverview(Menu1StepCDetailsDto.Overview.Find.Params params) {
        Menu1StepCDetailsDto.Overview.Find.Result result = menu1StepCService.getOverView(params);
        return ResponseEntity.ok(result);
    }
}
