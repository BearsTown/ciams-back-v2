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

import com.uitgis.ciams.dto.CiamsPlanContentLinkDto;
import com.uitgis.ciams.model.CiamsPlanContentLink;
import com.uitgis.ciams.service.CiamsPlanContentLinkService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/v1/plan/contentLink")
@RestController
@AllArgsConstructor
public class CiamsPlanContentLinkController {

	final private CiamsPlanContentLinkService ciamsPlanContentLinkService;

	@GetMapping
    public ResponseEntity<?> selectList(CiamsPlanContentLinkDto.Select params) throws Exception {

		List<CiamsPlanContentLink> list = ciamsPlanContentLinkService.selectList(params);

        return ResponseEntity.ok(list);
    }

	@PostMapping
    public ResponseEntity<?> add(@RequestBody CiamsPlanContentLinkDto.Add add) throws Exception {

		int count = ciamsPlanContentLinkService.insert(add);

        return ResponseEntity.ok(count > 0 ? true : false);
    }

	@PostMapping("/addArea/{planContentId}")
    public ResponseEntity<?> addArea(@PathVariable String planContentId) throws Exception {

		int count = ciamsPlanContentLinkService.insertArea(planContentId);

        return ResponseEntity.ok(count > 0 ? true : false);
    }

	@PutMapping
	public ResponseEntity<?> modify(@RequestBody List<CiamsPlanContentLinkDto.Modify> list) throws Exception {

		int count = ciamsPlanContentLinkService.update(list);

		return ResponseEntity.ok(count > 0 ? true : false);
	}

	@DeleteMapping("/{planContentLinkId}/{planContentId}")
	public ResponseEntity<?> remove(@PathVariable String planContentLinkId, @PathVariable String planContentId) throws Exception {
		CiamsPlanContentLinkDto.Delete params = new CiamsPlanContentLinkDto.Delete();
		params.setPlanContentLinkId(planContentLinkId);
		params.setPlanContentId(planContentId);
		int count = ciamsPlanContentLinkService.delete(params);

        return ResponseEntity.ok(count > 0 ? true : false);
	}
}
