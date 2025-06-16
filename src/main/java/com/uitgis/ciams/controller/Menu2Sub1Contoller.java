package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.Menu2Sub1DetailsDto;
import com.uitgis.ciams.service.Menu2Sub1Service;
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
public class Menu2Sub1Contoller {

    private final Menu2Sub1Service menu2Sub1Service;

    @GetMapping("/ita/{zoneNo}")
    public ResponseEntity<List<Menu2Sub1DetailsDto.ItaResultData>> getByZoneNoItaResultDatas(@PathVariable("zoneNo") String zoneNo) {
        List<Menu2Sub1DetailsDto.ItaResultData> result = menu2Sub1Service.getByZoneNoItaResultDatas(zoneNo);
        return ResponseEntity.ok(result);
    }

//    @GetMapping("/overview")
//    public ResponseEntity<Menu2ZoneDetailsDto.Overview.Find.Result> getMenu2Sub1OverView(Menu2ZoneDetailsDto.Overview.Find.Params params) {
//        Menu2ZoneDetailsDto.Overview.Find.Result result = ciamsMenu2Sub1Service.getMenu2Sub1OverView(params);
//        return ResponseEntity.ok(result);
//    }

//    @GetMapping("/group/{id}")
//    public ResponseEntity<?> getDataGroups(@PathVariable("id") int parentId) {
//        List<CiamsDataGroup> result = ciamsMenu2Sub1Service.getDataGroups(parentId);
//        return ResponseEntity.ok(result);
//    }
//
//    @GetMapping("/config/{id}")
//    public ResponseEntity<?> getDataConfig(@PathVariable("id") int dataGroupId) {
//        CiamsMenu2Sub2Dto.DataConfig result = ciamsMenu2Sub1Service.getDataConfig(dataGroupId);
//        return ResponseEntity.ok(result);
//    }
}
