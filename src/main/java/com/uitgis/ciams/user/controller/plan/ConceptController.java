package com.uitgis.ciams.user.controller.plan;

import com.uitgis.ciams.user.dto.plan.concept.ConceptDto;
import com.uitgis.ciams.model.CiamsImage;
import com.uitgis.ciams.user.service.plan.ConceptService;
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
@RequestMapping("/api/v1/plan/concept")
@RestController
public class ConceptController {
    private final ConceptService conceptService;


    /**
     * 기본구상 대상지 상세정보
     *
     * @param zoneNo
     * @return
     */
    @GetMapping("/{zoneNo}/info")
    public ResponseEntity<ConceptDto.Info.Find.Result> getConceptInfo(@PathVariable("zoneNo") String zoneNo) {
        ConceptDto.Info.Find.Result result = conceptService.getConceptInfo(zoneNo);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/image/{id}")
    public ResponseEntity<?> viewImage(@PathVariable("id") int id) {
        CiamsImage fileInfo = conceptService.getImage(id);
        File imageFile = FileUtil.getFile(fileInfo);

        return FileUtil.responseEntityImage(imageFile);
    }

}
