package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.Ciams1_1_3Dto;
import com.uitgis.ciams.service.Ciams1_1_3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/1-1-3")
@RestController
public class Ciams1_1_3Contoller {

    private final Ciams1_1_3Service ciams1_1_3Service;

    @GetMapping("/ita/{sggCd}")
    public ResponseEntity<List<Ciams1_1_3Dto.ItaData>> getItaDatas(@PathVariable("sggCd") String sggCd) {
        List<Ciams1_1_3Dto.ItaData> result = ciams1_1_3Service.getItaDatas(sggCd);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/areas")
    public ResponseEntity<List<Ciams1_1_3Dto.IndustryArea>> getIndustryAreas() {
        List<Ciams1_1_3Dto.IndustryArea> result = ciams1_1_3Service.getIndustryAreas(true);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/ita/result")
    public ResponseEntity<Ciams1_1_3Dto.Search.Result> getItaResultDataList(Ciams1_1_3Dto.Search.Params params) {
        Ciams1_1_3Dto.Search.Result result = ciams1_1_3Service.getItaResultDataList(params);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/ita/status")
    public ResponseEntity<Ciams1_1_3Dto.IndustryStatus.Find.Result> getIndustryStatus(@Param("params") @RequestBody
                                                                     Ciams1_1_3Dto.IndustryStatus.Find.Params params) {
        Ciams1_1_3Dto.IndustryStatus.Find.Result result = ciams1_1_3Service.getIndustryStatus(params);
        return ResponseEntity.ok(result);
    }
}
