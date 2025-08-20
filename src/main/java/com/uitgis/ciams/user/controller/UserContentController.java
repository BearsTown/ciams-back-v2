package com.uitgis.ciams.user.controller;

import com.uitgis.ciams.model.ContentMenu;
import com.uitgis.ciams.user.dto.ContentDto;
import com.uitgis.ciams.user.dto.ContentMenuDto;
import com.uitgis.ciams.user.service.ContentService;
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
@RequestMapping("/api/v1/content")
@RestController
public class UserContentController {
    private final ContentService contentService;


    @GetMapping("/menu/group")
    public ResponseEntity<?> getContentMenuGroupList() {
        List<ContentMenu> result = contentService.getContentMenuGroupList();
        return ResponseEntity.ok(result);
    }


    @GetMapping("/menu/{parentId}/list")
    public ResponseEntity<List<ContentMenuDto.Find.Result>> getContentMenuList(@PathVariable("parentId") int parentId) {
        List<ContentMenuDto.Find.Result> result = contentService.getContentMenuList(ContentMenuDto.Find.Params
                .builder()
                .parentId(parentId)
                .build());
        return ResponseEntity.ok(result);
    }


    @GetMapping("/{menuId}/list")
    public ResponseEntity<List<ContentDto.Find.Result>> getContentList(@PathVariable("menuId") int menuId) {
        List<ContentDto.Find.Result> result = contentService.getContentList(ContentDto.Find.Params
                .builder()
                .menuId(menuId)
                .build());
        return ResponseEntity.ok(result);
    }
}
