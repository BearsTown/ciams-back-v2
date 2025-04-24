package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.Ciams1_1_2Dto;
import com.uitgis.ciams.service.Ciams1_1_2Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/1-1-2")
@RestController
public class Ciams1_1_2Contoller {

    private final Ciams1_1_2Service ciams1_1_2Service;

    @GetMapping("/density/{type}")
    public ResponseEntity<Ciams1_1_2Dto.DensityInfo> getDensityInfos(@PathVariable("type") String type) {
        Ciams1_1_2Dto.DensityInfo result = ciams1_1_2Service.getDensityInfos(type);
        return ResponseEntity.ok(result);
    }
}
