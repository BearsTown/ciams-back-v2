package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.CiamsMenu2Sub1DetailsDto;
import com.uitgis.ciams.dto.CiamsMenu3Sub1DetailsDto;
import com.uitgis.ciams.model.CiamsFile;
import com.uitgis.ciams.model.CiamsImage;
import com.uitgis.ciams.service.CiamsMenu2Sub1Service;
import com.uitgis.ciams.service.CiamsMenu3Sub1Service;
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
@RequestMapping("/api/v1/menu-3/sub-1")
@RestController
public class CiamsMenu3Sub1Contoller {

    private final CiamsMenu3Sub1Service ciamsMenu3Sub1Service;

    /**
     * 상세정보 개요
     */
    @GetMapping("/overview")
    public ResponseEntity<CiamsMenu3Sub1DetailsDto.Overview.Find.Result> getMenu2Sub1OverView(CiamsMenu3Sub1DetailsDto.Overview.Find.Params params) {
        CiamsMenu3Sub1DetailsDto.Overview.Find.Result result = ciamsMenu3Sub1Service.getMenu3Sub1OverView(params);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<?> viewImage(@PathVariable("id") int id) {
        CiamsImage fileInfo = ciamsMenu3Sub1Service.getImage(id);
        File imageFile = FileUtil.getFile(fileInfo);

        return FileUtil.responseEntityImage(imageFile);
    }
}
