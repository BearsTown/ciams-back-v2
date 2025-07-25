package com.uitgis.ciams.controller.classifyAnalysis;

import com.uitgis.ciams.dto.classifyAnalysis.comprehensive.ClassComprehensiveDto;
import com.uitgis.ciams.model.CiamsImage;
import com.uitgis.ciams.service.classifyAnalysis.ClassComprehensiveService;
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
@RequestMapping("/api/v1/class/comprehensive")
@RestController
public class ClassComprehensiveController {
    private final ClassComprehensiveService classComprehensiveService;


    /**
     * 유형화종합분석 상세정보
     *
     * @param zoneNo
     * @return
     */
    @GetMapping("/{zoneNo}/info")
    public ResponseEntity<ClassComprehensiveDto.Info> getComprehensiveInfo(@PathVariable("zoneNo") String zoneNo) {
        ClassComprehensiveDto.Info result = classComprehensiveService.getComprehensiveInfo(zoneNo);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/image/{id}")
    public ResponseEntity<?> viewImage(@PathVariable("id") int id) {
        CiamsImage fileInfo = classComprehensiveService.getImage(id);
        File imageFile = FileUtil.getFile(fileInfo);

        return FileUtil.responseEntityImage(imageFile);
    }

}
