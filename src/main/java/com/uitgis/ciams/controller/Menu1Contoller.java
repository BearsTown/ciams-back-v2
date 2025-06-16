package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.CiamsMenu2Sub2Dto;
import com.uitgis.ciams.model.CiamsDataGroup;
import com.uitgis.ciams.service.Menu1Service;
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
@RequestMapping("/api/v1/menu-1")
@RestController
public class Menu1Contoller {

    private final Menu1Service menu1Service;

    @GetMapping("/group/{id}")
    public ResponseEntity<?> getDataGroups(@PathVariable("id") int parentId) {
        List<CiamsDataGroup> result = menu1Service.getDataGroups(parentId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/config/{id}")
    public ResponseEntity<?> getDataConfig(@PathVariable("id") int dataGroupId) {
        CiamsMenu2Sub2Dto.DataConfig result = menu1Service.getDataConfig(dataGroupId);
        return ResponseEntity.ok(result);
    }
}
