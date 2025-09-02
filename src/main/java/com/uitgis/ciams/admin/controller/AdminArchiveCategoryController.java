package com.uitgis.ciams.admin.controller;

import com.uitgis.ciams.enums.ActionTypeEnum;
import com.uitgis.ciams.enums.MenuEnum;
import com.uitgis.ciams.exception.CustomException;
import com.uitgis.ciams.model.CiamsArchiveCategory;
import com.uitgis.ciams.admin.service.AdminArchiveCategoryService;
import com.uitgis.ciams.admin.service.AdminArchiveService;
import com.uitgis.ciams.service.CiamsCommonService;
import com.uitgis.ciams.admin.dto.ArchiveCategoryDto;
import com.uitgis.ciams.admin.dto.ArchiveDto;
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

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * 아카이브 카테고리
 * @author lee
 *
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/category")
@RestController
public class AdminArchiveCategoryController {

	private final AdminArchiveCategoryService adminArchiveCategoryService;
	private final AdminArchiveService adminArchiveService;
	private final CiamsCommonService ciamsCommonService;


	/**
	 * 다건 조회
	 */
	@GetMapping
	public ResponseEntity<?> list(ArchiveCategoryDto.Find param) {
		List<ArchiveCategoryDto.ListResult> resultList = adminArchiveCategoryService.getList(param);

		return ResponseEntity.ok(resultList);
	}


	/**
	 * 신규 추가
	 */
	@PostMapping("/add")
	public ResponseEntity<CiamsArchiveCategory> add(@RequestBody CiamsArchiveCategory param, Principal principal) throws Exception {
		param.setRegUser(principal.getName());
		CiamsArchiveCategory ciamsArchiveCategory = adminArchiveCategoryService.add(param);

		ciamsCommonService.log(MenuEnum.ARCHIVE_CATEGORY, ActionTypeEnum.ADD, ciamsArchiveCategory.getCategoryId());

		return ResponseEntity.ok(ciamsArchiveCategory);
	}


	/**
	 * 정보 변경
	 */
	@PutMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody CiamsArchiveCategory param) throws Exception {
		CiamsArchiveCategory ciamsArchiveCategory = adminArchiveCategoryService.modify(param);

		ciamsCommonService.log(MenuEnum.ARCHIVE_CATEGORY, ActionTypeEnum.UPDATE, ciamsArchiveCategory.getCategoryId());
		return ResponseEntity.ok(ciamsArchiveCategory);
	}


	/**
	 * 정렬 순서 변경
	 */
	@PutMapping("/priority")
	public ResponseEntity<?> modifyPriority(@RequestBody List<String> categoryIds) {
		adminArchiveCategoryService.changePriority(categoryIds);
		return ResponseEntity.ok().build();
	}


	/**
	 * 삭제
	 */
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?> remove(@PathVariable String categoryId) throws Exception {
		ArchiveDto.Find dto = new ArchiveDto.Find();
		dto.setCategoryId(categoryId);

		Map<String, Object> map = adminArchiveService.getListWithFile(dto);

		List<ArchiveDto.ListResult> list = (List<ArchiveDto.ListResult>) map.get("list");
		if(!list.isEmpty()) {
			throw new CustomException("기존에 등록된 게시글이 존재합니다.");
		}

		adminArchiveCategoryService.remove(categoryId);

		ciamsCommonService.log(MenuEnum.ARCHIVE_CATEGORY, ActionTypeEnum.DELETE, categoryId);
		return ResponseEntity.ok().build();
	}

}
