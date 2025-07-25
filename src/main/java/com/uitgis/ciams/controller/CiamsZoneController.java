package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.CiamsZoneDto;
import com.uitgis.ciams.service.CiamsZoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/zone")
@RestController
public class CiamsZoneController {
    private final CiamsZoneService ciamsZoneService;


    /**
     * 대상지 목록
     *
     * @param params
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<CiamsZoneDto.Search.Result> getCiamsZones(CiamsZoneDto.Search.Params params) {
        CiamsZoneDto.Search.Result result = ciamsZoneService.getCiamsZones(params);
        return ResponseEntity.ok(result);
    }


    /**
     * 대상지 개요
     */
    @GetMapping("/overview")
    public ResponseEntity<CiamsZoneDto.Overview.Find.Result> getCiamsZoneOverView(CiamsZoneDto.Overview.Find.Params params) {
        CiamsZoneDto.Overview.Find.Result result = ciamsZoneService.getCiamsZoneOverView(params);
        return ResponseEntity.ok(result);
    }

}
