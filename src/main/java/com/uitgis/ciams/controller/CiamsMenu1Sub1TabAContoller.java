package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.CiamsMenu1Sub1TabADto;
import com.uitgis.ciams.service.CiamsMenu1Sub1TabAService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/status")
@RestController
public class CiamsMenu1Sub1TabAContoller {

    private final CiamsMenu1Sub1TabAService ciamsMenu1Sub1TabAService;

    @GetMapping("/group/{statusId}")
    public ResponseEntity<List<CiamsMenu1Sub1TabADto.StatusGroup>> getItaDatas(@PathVariable("statusId") int statusId) {
        List<CiamsMenu1Sub1TabADto.StatusGroup> result = ciamsMenu1Sub1TabAService.getStatusGroups(statusId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{dataId}/info")
    public ResponseEntity<CiamsMenu1Sub1TabADto.DataInfo> getDataInfo(@PathVariable("dataId") int dataId) {
        CiamsMenu1Sub1TabADto.DataInfo result = ciamsMenu1Sub1TabAService.getDataInfo(dataId);
        return ResponseEntity.ok(result);
    }
}
