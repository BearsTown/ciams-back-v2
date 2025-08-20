package com.uitgis.ciams.user.controller.classifyAnalysis;

import com.uitgis.ciams.user.dto.classifyAnalysis.ita.ClassItaDto;
import com.uitgis.ciams.user.service.classifyAnalysis.ClassItaService;
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
@RequestMapping("/api/v1/class/ita")
@RestController
public class ClassItaController {
    private final ClassItaService classItaService;


    /**
     * 산업기반분석 결과
     *
     * @param zoneNo
     * @return
     */
    @GetMapping("/{zoneNo}/ita")
    public ResponseEntity<List<ClassItaDto.ItaResultData>> getItaResultDataById(@PathVariable("zoneNo") String zoneNo) {
        List<ClassItaDto.ItaResultData> result = classItaService.getItaResultDataById(zoneNo);
        return ResponseEntity.ok(result);
    }

}
