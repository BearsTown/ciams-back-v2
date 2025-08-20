package com.uitgis.ciams.admin.controller;

import com.uitgis.ciams.admin.dto.ContentDto;
import com.uitgis.ciams.admin.service.AdminContentService;
import com.uitgis.ciams.service.CiamsFileService;
import com.uitgis.ciams.user.dto.CiamsFileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/content")
@RestController
public class AdminContentController {
    private final CiamsFileService ciamsFileService;
    private final AdminContentService adminContentService;


    @GetMapping("/{menuId}/list")
    public ResponseEntity<List<ContentDto.Find.Result>> getContentList(@PathVariable("menuId") int menuId) {
        List<ContentDto.Find.Result> result = adminContentService.getContentList(ContentDto.Find.Params
                .builder()
                .menuId(menuId)
                .build());
        return ResponseEntity.ok(result);
    }


    @GetMapping("/{id}/info")
    public ResponseEntity<ContentDto.Find.Result> getContentInfo(@PathVariable("id") int id) {
        ContentDto.Find.Result result = adminContentService.getContent(ContentDto.Find.Params
                .builder()
                .id(id)
                .build());
        return ResponseEntity.ok(result);
    }


    @PostMapping("/add")
    public ResponseEntity<Integer> addContent(@RequestBody ContentDto.Add params) {
        return ResponseEntity.ok(adminContentService.addContent(params));
    }


    @PutMapping("/update")
    public ResponseEntity<Integer> updateContent(@RequestBody ContentDto.Update params) {
        return ResponseEntity.ok(adminContentService.updateContent(params));
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Integer> deleteContent(@PathVariable int id) {
        return ResponseEntity.ok(adminContentService.deleteContent(id));
    }

    @PutMapping("/priority")
    public ResponseEntity<Integer> updatePriority(@RequestBody ContentDto.Priority params) {
        return ResponseEntity.ok(adminContentService.updatePriority(params));
    }

    @PostMapping("/image")
    public ResponseEntity<?> uploadImageFile(@RequestParam List<MultipartFile> files) throws Exception {
        List<CiamsFileDto.TempFile> tempFiles = ciamsFileService.uploadTempFiles(files);

        return ResponseEntity.ok(tempFiles);
    }
}
