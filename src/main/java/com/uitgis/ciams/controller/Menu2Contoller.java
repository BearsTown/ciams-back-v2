package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.Menu2ZoneDetailsDto;
import com.uitgis.ciams.service.Menu1Service;
import com.uitgis.ciams.service.Menu2Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu-2")
@RestController
public class Menu2Contoller {

    private final Menu2Service menu2Service;

    /**
     * 상세정보 개요
     */
    @GetMapping("/overview")
    public ResponseEntity<Menu2ZoneDetailsDto.Overview.Find.Result> getMenu2Sub1OverView(Menu2ZoneDetailsDto.Overview.Find.Params params) {
        Menu2ZoneDetailsDto.Overview.Find.Result result = menu2Service.getMenu2Sub1OverView(params);
        return ResponseEntity.ok(result);
    }
}
