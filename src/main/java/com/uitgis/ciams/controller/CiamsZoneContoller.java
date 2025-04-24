package com.uitgis.ciams.controller;

import com.uitgis.ciams.service.CiamsZoneService;
import com.uitgis.gis.dto.GisCiamsZoneDTO;
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

    @GetMapping("/list")
    public ResponseEntity<GisCiamsZoneDTO.Search.Result> getCiamsZoneList(GisCiamsZoneDTO.Search.Params params) {
        GisCiamsZoneDTO.Search.Result result = ciamsZoneService.getCiamsZoneList(params);
        return ResponseEntity.ok(result);
    }

}
