package com.uitgis.ciams.user.controller;

import com.uitgis.ciams.admin.dto.ArchiveCategoryDto;
import com.uitgis.ciams.admin.service.AdminArchiveCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 아카이브 카테고리
 *
 * @author lee
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
@RestController
public class CiamsArchiveCategoryController {

    private final AdminArchiveCategoryService adminArchiveCategoryService;


    /**
     * 다건 조회
     */
    @GetMapping
    public ResponseEntity<?> list(ArchiveCategoryDto.Find param) {
        List<ArchiveCategoryDto.ListResult> resultList = adminArchiveCategoryService.getList(param);

        return ResponseEntity.ok(resultList);
    }

}
