package com.uitgis.ciams.admin.controller;

import com.uitgis.ciams.admin.dto.ContentMenuDto;
import com.uitgis.ciams.admin.service.AdminContentMenuService;
import com.uitgis.ciams.model.ContentMenu;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/content/menu")
@RestController
public class AdminContentMenuController {
    private final AdminContentMenuService adminContentMenuService;


    @GetMapping("/group")
    public ResponseEntity<?> getContentMenuGroupList() {
        List<ContentMenu> result = adminContentMenuService.getContentMenuGroupList();
        return ResponseEntity.ok(result);
    }


    @GetMapping("/{parentId}/list")
    public ResponseEntity<List<ContentMenuDto.Find.Result>> getContentMenuList(@PathVariable("parentId") int parentId) {
        List<ContentMenuDto.Find.Result> result = adminContentMenuService.getContentMenuList(ContentMenuDto.Find.Params
                .builder()
                .parentId(parentId)
                .build());
        return ResponseEntity.ok(result);
    }


    @GetMapping("/{id}/info")
    public ResponseEntity<ContentMenu> getContentMenuInfo(@PathVariable("id") int id) {
        ContentMenu result = adminContentMenuService.getContentMenu(id);
        return ResponseEntity.ok(result);
    }


    @PostMapping("/add")
    public ResponseEntity<Integer> addContentMenu(@RequestBody ContentMenuDto.Add params) {
        return ResponseEntity.ok(adminContentMenuService.addContentMenu(params));
    }


    @PutMapping("/update")
    public ResponseEntity<Integer> updateContentMenu(@RequestBody ContentMenuDto.Update params) {
        return ResponseEntity.ok(adminContentMenuService.updateContentMenu(params));
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Integer> deleteContentMenu(@PathVariable int id) {
        return ResponseEntity.ok(adminContentMenuService.deleteContentMenu(id));
    }
}
