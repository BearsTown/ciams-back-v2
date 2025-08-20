package com.uitgis.ciams.user.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uitgis.ciams.user.dto.CiamsArchiveCategoryDto;
import com.uitgis.ciams.user.dto.CiamsArchiveDto;
import com.uitgis.ciams.enums.ActionTypeEnum;
import com.uitgis.ciams.enums.MenuEnum;
import com.uitgis.ciams.exception.CustomException;
import com.uitgis.ciams.model.CiamsArchiveCategory;
import com.uitgis.ciams.service.CiamsArchiveCategoryService;
import com.uitgis.ciams.service.CiamsArchiveService;
import com.uitgis.ciams.service.CiamsCommonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 아카이브 카테고리
 * @author lee
 *
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/category")
@RestController
public class CiamsArchiveCategoryController {

	private final CiamsArchiveCategoryService ciamsArchiveCategoryService;
	private final CiamsArchiveService ciamsArchiveService;
	private final CiamsCommonService ciamsCommonService;

	/**
	 * 다건 조회
	 */
	@GetMapping
	public ResponseEntity<?> list(CiamsArchiveCategoryDto.Find param) {

		List<CiamsArchiveCategoryDto.ListResult> resultList = ciamsArchiveCategoryService.getList(param);

		return ResponseEntity.ok(resultList);
	}

	/**
	 * 신규 추가
	 */
	@PostMapping("add")
	public ResponseEntity<CiamsArchiveCategory> add(@RequestBody CiamsArchiveCategory param, Principal principal) throws Exception {

		param.setRegUser(principal.getName());
		CiamsArchiveCategory ciamsArchiveCategory = ciamsArchiveCategoryService.add(param);

		ciamsCommonService.log(MenuEnum.ARCHIVE_CATEGORY, ActionTypeEnum.ADD, ciamsArchiveCategory.getCategoryId());

		return ResponseEntity.ok(ciamsArchiveCategory);
	}

	/**
	 * 정보 변경
	 */
	@PutMapping("modify")
	public ResponseEntity<?> modify(@RequestBody CiamsArchiveCategory param) throws Exception {

		CiamsArchiveCategory ciamsArchiveCategory = ciamsArchiveCategoryService.modify(param);

		ciamsCommonService.log(MenuEnum.ARCHIVE_CATEGORY, ActionTypeEnum.UPDATE, ciamsArchiveCategory.getCategoryId());
		return ResponseEntity.ok(ciamsArchiveCategory);
	}

	/**
	 * 정렬 순서 변경
	 */
	@PutMapping("priority")
	public ResponseEntity<?> modifyPriority(@RequestBody List<String> categoryIds) {
		ciamsArchiveCategoryService.changePriority(categoryIds);
		return ResponseEntity.ok().build();
	}


	/**
	 * 삭제
	 */
	@DeleteMapping("{categoryId}")
	public ResponseEntity<?> remove(@PathVariable String categoryId) throws Exception {

		CiamsArchiveDto.Find dto = new CiamsArchiveDto.Find();
		dto.setCategoryId(categoryId);

		Map<String, Object> map = ciamsArchiveService.getListWithFile(dto);

		List<CiamsArchiveDto.ListResult> list = (List<CiamsArchiveDto.ListResult>) map.get("list");
		if(list.size() > 0) {
			throw new CustomException("기존에 등록된 게시글이 존재합니다.");
		}

		ciamsArchiveCategoryService.remove(categoryId);

		ciamsCommonService.log(MenuEnum.ARCHIVE_CATEGORY, ActionTypeEnum.DELETE, categoryId);
		return ResponseEntity.ok().build();
	}

}
