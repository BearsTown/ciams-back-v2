package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.CiamsPlanAreaIncenDto;
import com.uitgis.ciams.service.CiamsExcelService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/v1/excel")
@RestController
@RequiredArgsConstructor
public class CiamsExcelController {
    private final CiamsExcelService excelService;

    @GetMapping("/incen/download")
    public ResponseEntity<?> downloadExcelFile(CiamsPlanAreaIncenDto.Find.Incentive params, HttpServletResponse response) throws Exception {
        excelService.DownLoadIncen(response, params);
        return ResponseEntity.ok(null);
    }

}

