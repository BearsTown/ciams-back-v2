package com.uitgis.ciams.user.controller;

import com.uitgis.ciams.user.dto.CiamsArchiveDto;
import com.uitgis.ciams.user.service.CiamsArchiveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 아카이브
 *
 * @author lee
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/archive")
@RestController
public class CiamsArchiveController {
    private final CiamsArchiveService ciamsArchiveService;


    /**
     * 단건 조회
     */
    @GetMapping("/{archiveId}")
    public ResponseEntity<CiamsArchiveDto.WithFile> archiveDetail(@PathVariable String archiveId)
            throws Exception {

        CiamsArchiveDto.WithFile archive = ciamsArchiveService.getDetailById(archiveId);
        return ResponseEntity.ok(archive);
    }


    /**
     * 파일 정보를 포함하는 다건 조회
     */
    @GetMapping
    public ResponseEntity<?> list(CiamsArchiveDto.Find param) {
        Map<String, Object> list = ciamsArchiveService.getListWithFile(param);
        return ResponseEntity.ok(list);
    }
}
