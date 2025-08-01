package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.CiamsSourceGroupDto;
import com.uitgis.ciams.service.CiamsSourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/source")
@RestController
public class SourceController {
    private final CiamsSourceService ciamsSourceService;


    /**
     * 출처 목록
     *
     * @param params
     * @return
     */
    @PostMapping("/list")
    public ResponseEntity<List<CiamsSourceGroupDto.Find.Result>> getSources(@RequestBody CiamsSourceGroupDto.Find.Params params) {
        List<CiamsSourceGroupDto.Find.Result> result = ciamsSourceService.getSources(params);
        return ResponseEntity.ok(result);
    }

}
