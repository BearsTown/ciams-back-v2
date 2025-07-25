package com.uitgis.ciams.controller;

import com.uitgis.ciams.service.CiamsDistService;
import com.uitgis.gis.dto.GisCiamsDistDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/dist")
@RestController
public class CiamsDistController {
    private final CiamsDistService ciamsDistService;


    @GetMapping("/list")
    public ResponseEntity<GisCiamsDistDTO.Search.Result> getCiamsDistList(GisCiamsDistDTO.Search.Params params) {
        GisCiamsDistDTO.Search.Result result = ciamsDistService.getCiamsDistList(params);
        return ResponseEntity.ok(result);
    }

}
