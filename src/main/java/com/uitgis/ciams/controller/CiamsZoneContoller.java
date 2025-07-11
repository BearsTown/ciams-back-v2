package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.CiamsZoneDTO;
import com.uitgis.ciams.dto.Menu2ZoneDetailsDto;
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
public class CiamsZoneContoller {

    private final CiamsZoneService ciamsZoneService;

    /**
     * 대상지 목록
     *
     * @param params
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<CiamsZoneDTO.Search.Result> getCiamsZoneList(CiamsZoneDTO.Search.Params params) {
        CiamsZoneDTO.Search.Result result = ciamsZoneService.getCiamsZoneList(params);
        return ResponseEntity.ok(result);
    }


    /**
     * 대상지 개요
     */
    @GetMapping("/overview")
    public ResponseEntity<CiamsZoneDTO.Overview.Find.Result> getCiamsZoneOverView(CiamsZoneDTO.Overview.Find.Params params) {
        CiamsZoneDTO.Overview.Find.Result result = ciamsZoneService.getCiamsZoneOverView(params);
        return ResponseEntity.ok(result);
    }
}
