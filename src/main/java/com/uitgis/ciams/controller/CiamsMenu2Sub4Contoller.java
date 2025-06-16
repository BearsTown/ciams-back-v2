package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.CiamsMenu2Sub4DetailsDto;
import com.uitgis.ciams.model.CiamsImage;
import com.uitgis.ciams.service.CiamsMenu2Sub4Service;
import com.uitgis.ciams.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu-2/sub-4")
@RestController
public class CiamsMenu2Sub4Contoller {

    private final CiamsMenu2Sub4Service ciamsMenu2Sub4Service;

    /**
     * 상세정보 개요
     */
    @GetMapping("/overview")
    public ResponseEntity<CiamsMenu2Sub4DetailsDto.Overview.Find.Result> getMenu2Sub4OverView(CiamsMenu2Sub4DetailsDto.Overview.Find.Params params) {
        CiamsMenu2Sub4DetailsDto.Overview.Find.Result result = ciamsMenu2Sub4Service.getMenu2Sub4OverView(params);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/info/{zoneNo}")
    public ResponseEntity<CiamsMenu2Sub4DetailsDto.Info> getMenu2Sub4Info(@PathVariable("zoneNo") String zoneNo) {
        CiamsMenu2Sub4DetailsDto.Info result = ciamsMenu2Sub4Service.getAnalysisInfo(zoneNo);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<?> viewImage(@PathVariable("id") int id) {
        CiamsImage fileInfo = ciamsMenu2Sub4Service.getImage(id);
        File imageFile = FileUtil.getFile(fileInfo);

        return FileUtil.responseEntityImage(imageFile);
    }
}
