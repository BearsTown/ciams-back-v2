package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.Menu3Sub1DetailsDto;
import com.uitgis.ciams.model.CiamsImage;
import com.uitgis.ciams.service.Menu3Sub1Service;
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
public class Menu3Sub1Contoller {

    private final Menu3Sub1Service menu3Sub1Service;

    /**
     * 상세정보 개요
     */
//    @GetMapping("/overview")
//    public ResponseEntity<CiamsMenu3Sub1DetailsDto.Overview.Find.Result> getMenu2Sub1OverView(CiamsMenu3Sub1DetailsDto.Overview.Find.Params params) {
//        CiamsMenu3Sub1DetailsDto.Overview.Find.Result result = ciamsMenu3Sub1Service.getMenu3Sub1OverView(params);
//        return ResponseEntity.ok(result);
//    }

    @GetMapping("/info")
    public ResponseEntity<Menu3Sub1DetailsDto.Info.Find.Result> getMenu2Sub1Info(Menu3Sub1DetailsDto.Info.Find.Params params) {
        Menu3Sub1DetailsDto.Info.Find.Result result = menu3Sub1Service.getMenu3Sub1Info(params);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<?> viewImage(@PathVariable("id") int id) {
        CiamsImage fileInfo = menu3Sub1Service.getImage(id);
        File imageFile = FileUtil.getFile(fileInfo);

        return FileUtil.responseEntityImage(imageFile);
    }
}
