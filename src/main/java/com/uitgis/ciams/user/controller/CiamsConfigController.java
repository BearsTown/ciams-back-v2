package com.uitgis.ciams.user.controller;

import com.uitgis.ciams.exception.CustomException;
import com.uitgis.ciams.model.CiamsConfig;
import com.uitgis.ciams.user.dto.CiamsConfigDto;
import com.uitgis.ciams.user.service.CiamsConfigService;
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
@RestController
@RequestMapping("/api/v1/config")
public class CiamsConfigController {
    private final CiamsConfigService ciamsConfigService;


    @GetMapping
    public ResponseEntity<List<CiamsConfigDto.WithFile>> list(CiamsConfigDto.Find param) {
        List<CiamsConfigDto.WithFile> list = ciamsConfigService.getLoadConfigInfo(param);
        return ResponseEntity.ok(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CiamsConfig> getConfig(@PathVariable String id) throws CustomException {
        CiamsConfigDto.WithFile ciamsConfig = ciamsConfigService.getConfById(id)
                .orElseThrow(() -> new CustomException("config info does not exist"));

        return ResponseEntity.ok(ciamsConfig);
    }
}
