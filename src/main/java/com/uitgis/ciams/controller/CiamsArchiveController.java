package com.uitgis.ciams.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import com.uitgis.ciams.dto.CiamsArchiveDto;
import com.uitgis.ciams.enums.ActionTypeEnum;
import com.uitgis.ciams.enums.MenuEnum;
import com.uitgis.ciams.service.CiamsArchiveService;
import com.uitgis.ciams.service.CiamsCommonService;
import com.uitgis.ciams.service.CiamsFileService;
import com.uitgis.ciams.util.ValidUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 아카이브
 * @author lee
 *
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/archive")
@RestController
public class CiamsArchiveController {

	private final CiamsArchiveService ciamsArchiveService;
	private final CiamsFileService ciamsFileService;
	private final CiamsCommonService ciamsCommonService;

	/**
	 * 단건 조회
	 */
	@GetMapping("{archiveId}")
	public ResponseEntity<CiamsArchiveDto.WithFile> archiveDetail(@PathVariable String archiveId)
			throws Exception {

		CiamsArchiveDto.WithFile archive = ciamsArchiveService.getDetailById(archiveId);
		return ResponseEntity.ok(archive);
	}

	/**
	 * 파일 정보를 포함하는 다건 조회
	 *
	 */
	@GetMapping
	public ResponseEntity<?> list(CiamsArchiveDto.Find param) {

		Map<String, Object> list = ciamsArchiveService.getListWithFile(param);
		return ResponseEntity.ok(list);

	}

	/**
	 * 등록(관리자)
	 */
	@PostMapping
	public ResponseEntity<CiamsArchiveDto.WithFile> add(CiamsArchiveDto.Add param, Principal principal)
			throws Exception {

		String userName = principal.getName();
		param.setRegUser(userName);
		CiamsArchiveDto.WithFile result = ciamsArchiveService.add(param);

		//대표이미지 추가
		if(ValidUtil.notEmpty(param.getImgFile())) {
			List<MultipartFile> _files = new ArrayList<>();
			_files.add(param.getImgFile());
			ciamsFileService.uploadFiles(_files, "archiveImg", param.getArchiveId());
		}

		//첨부파일 추가
		if(ValidUtil.notEmpty(param.getAttachFiles()) && param.getAttachFiles().size() > 0) {
			ciamsFileService.uploadFiles(param.getAttachFiles(), "archive", param.getArchiveId());
		}

		ciamsCommonService.log(MenuEnum.ARCHIVE_CATEGORY, ActionTypeEnum.ADD, result.getArchiveId());

		return ResponseEntity.ok(result);
	}

	/**
	 * 수정(관리자)
	 * @throws Exception
	 */
	@PutMapping("{archiveId}")
	public ResponseEntity<CiamsArchiveDto.WithFile> modify(@PathVariable String archiveId
			, CiamsArchiveDto.Modify param, Principal principal)
			throws Exception {


		String userName = principal.getName();
		param.setRegUser(userName);
		CiamsArchiveDto.WithFile result = ciamsArchiveService.modify(param);

		//대표이미지 추가
		if(ValidUtil.notEmpty(param.getImgFile())) {
			List<MultipartFile> _files = new ArrayList<>();
			_files.add(param.getImgFile());
			ciamsFileService.uploadFiles(_files, "archiveImg", param.getArchiveId());
		}

		//첨부파일 추가
		if(ValidUtil.notEmpty(param.getAttachFiles()) && param.getAttachFiles().size() > 0) {
			ciamsFileService.uploadFiles(param.getAttachFiles(), "archive", param.getArchiveId());
		}

		//이전 파일 삭제(대표,첨부파일)
		if(ValidUtil.notEmpty(param.getRemoveFilesIds()) && param.getRemoveFilesIds().size() > 0) {
			ciamsFileService.deleteFilesByIds(param.getRemoveFilesIds());
		}

		ciamsCommonService.log(MenuEnum.ARCHIVE_CATEGORY, ActionTypeEnum.UPDATE, result.getArchiveId());

		return ResponseEntity.ok(result);
	}


	/**
	 * 숨김처리 일괄 변경(관리자)
	 */
	@PutMapping
	public ResponseEntity<?> modifyHiddenByIds(@Validated @RequestBody CiamsArchiveDto.ModifyAll param, Principal principal) throws Exception {

		param.setUsername(principal.getName());
		ciamsArchiveService.modifyByIds(param);

		ciamsCommonService.log(MenuEnum.ARCHIVE_CATEGORY, ActionTypeEnum.UPDATE, param.getIds());

		return ResponseEntity.ok().build();
	}

	/**
	 * 단건 삭제(관리자)
	 */
	@DeleteMapping("{archiveId}")
	public ResponseEntity<?> remove(@PathVariable String archiveId) throws IOException {
		int rtn = ciamsArchiveService.removeId(archiveId);

		List<String> list = new ArrayList<>();
		list.add(archiveId);
		rtn += ciamsFileService.deleteFilesByLinkIds(list);

		ciamsCommonService.log(MenuEnum.ARCHIVE_CATEGORY, ActionTypeEnum.DELETE, archiveId);

		return ResponseEntity.ok(rtn > 0 ? true : false);
	}

	/**
	 * 일괄 삭제. 데이터 삭제후 파일도 같이 삭제(관리자)
	 * @throws IOException
	 */
	@DeleteMapping
	public ResponseEntity<?> removeAll(@RequestParam List<String> ids, Principal principal) throws IOException{

		CiamsArchiveDto.ModifyAll param = new CiamsArchiveDto.ModifyAll();
		param.setIds(ids);
		param.setUsername(principal.getName());
		ciamsArchiveService.deleteByIds(param);

		List<String> list = new ArrayList<>();
		list.addAll(ids);
		int rtn = ciamsFileService.deleteFilesByLinkIds(list);

		ciamsCommonService.log(MenuEnum.ARCHIVE_CATEGORY, ActionTypeEnum.DELETE, ids);

		return ResponseEntity.ok(rtn > 0 ? true : false);
	}

}
