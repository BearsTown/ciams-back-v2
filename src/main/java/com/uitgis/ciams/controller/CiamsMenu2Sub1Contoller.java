package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.CiamsMenu2Sub1DetailsDto;
import com.uitgis.ciams.dto.CiamsMenu2Sub2Dto;
import com.uitgis.ciams.model.CiamsDataGroup;
import com.uitgis.ciams.service.CiamsMenu2Sub1Service;
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
@RequestMapping("/api/v1/menu-2/sub-1")
@RestController
public class CiamsMenu2Sub1Contoller {

    private final CiamsMenu2Sub1Service ciamsMenu2Sub1Service;

    /**
     * 상세정보 개요
     */
    @GetMapping("/overview")
    public ResponseEntity<CiamsMenu2Sub1DetailsDto.Overview.Find.Result> getMenu2Sub1OverView(CiamsMenu2Sub1DetailsDto.Overview.Find.Params params) {
        CiamsMenu2Sub1DetailsDto.Overview.Find.Result result = ciamsMenu2Sub1Service.getMenu2Sub1OverView(params);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<?> getDataGroups(@PathVariable("id") int parentId) {
        List<CiamsDataGroup> result = ciamsMenu2Sub1Service.getDataGroups(parentId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/config/{id}")
    public ResponseEntity<?> getDataConfig(@PathVariable("id") int dataGroupId) {
        CiamsMenu2Sub2Dto.DataConfig result = ciamsMenu2Sub1Service.getDataConfig(dataGroupId);
        return ResponseEntity.ok(result);
    }
}
