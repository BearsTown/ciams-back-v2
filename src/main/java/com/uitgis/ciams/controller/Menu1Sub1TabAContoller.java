package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.Menu1Sub1TabADto;
import com.uitgis.ciams.service.Menu1Sub1TabAService;
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
public class Menu1Sub1TabAContoller {

    private final Menu1Sub1TabAService menu1Sub1TabAService;

    @GetMapping("/tree")
    public ResponseEntity<List<Menu1Sub1TabADto.CiamsStatus>> getStatusTree() {
        List<Menu1Sub1TabADto.CiamsStatus> result = menu1Sub1TabAService.getStatusTree();
        return ResponseEntity.ok(result);
    }

//    @GetMapping("/group/{statusId}")
//    public ResponseEntity<List<CiamsMenu1Sub1TabADto.StatusGroup>> getItaDatas(@PathVariable("statusId") int statusId) {
//        List<CiamsMenu1Sub1TabADto.StatusGroup> result = ciamsMenu1Sub1TabAService.getStatusGroups(statusId);
//        return ResponseEntity.ok(result);
//    }

    @GetMapping("/group/{statusId}")
    public ResponseEntity<Menu1Sub1TabADto.CiamsStatusInfo> getItaDatas(@PathVariable("statusId") int statusId) {
        Menu1Sub1TabADto.CiamsStatusInfo result = menu1Sub1TabAService.getStatusInfo(statusId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{dataId}/info")
    public ResponseEntity<Menu1Sub1TabADto.DataInfo> getDataInfo(@PathVariable("dataId") int dataId) {
        Menu1Sub1TabADto.DataInfo result = menu1Sub1TabAService.getDataInfo(dataId);
        return ResponseEntity.ok(result);
    }
}
