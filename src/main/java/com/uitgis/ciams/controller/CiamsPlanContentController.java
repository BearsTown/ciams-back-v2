package com.uitgis.ciams.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uitgis.ciams.dto.CiamsPlanContentDto;
import com.uitgis.ciams.dto.CiamsPlanContentDto.SelectDetail;
import com.uitgis.ciams.service.CiamsPlanContentService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/v1/plan/content")
@RestController
@AllArgsConstructor
public class CiamsPlanContentController {

	final private CiamsPlanContentService contentService;

	@GetMapping("/area/list")
	public ResponseEntity<?> areaList(CiamsPlanContentDto.Search search) throws Exception{
		List<SelectDetail> list = contentService.selectAreaList(search);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/list")
	public ResponseEntity<?> list(CiamsPlanContentDto.Search search) throws Exception{
		List<SelectDetail> list = contentService.selectList(search);
		return ResponseEntity.ok(list);
	}

	@PostMapping("/detail")
    public ResponseEntity<?> addDetail(CiamsPlanContentDto.AddDetail add) throws Exception {

		int count = contentService.insert(add);

        return ResponseEntity.ok(count > 0 ? true : false);
    }

	@PutMapping
	public ResponseEntity<?> modify(CiamsPlanContentDto.Modify mod) throws Exception {

		int count = contentService.update(mod);

        return ResponseEntity.ok(count > 0 ? true : false);
	}

	@PutMapping("/priority")
	public ResponseEntity<?> changePriority(@RequestBody List<CiamsPlanContentDto.Priority> list) throws Exception {

		int count = contentService.changePriority("planContent", list);

		return ResponseEntity.ok(count > 0 ? true : false);
	}

	@DeleteMapping("/{type}/{id}")
	public ResponseEntity<?> remove(@PathVariable String type, @PathVariable String id) throws Exception {

		int count = contentService.delete(type, id);

        return ResponseEntity.ok(count > 0 ? true : false);
	}
}
