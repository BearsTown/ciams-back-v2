package com.uitgis.ciams.admin.controller;

import com.uitgis.ciams.admin.dto.ConfigDto;
import com.uitgis.ciams.admin.service.AdminConfigService;
import com.uitgis.ciams.enums.ActionTypeEnum;
import com.uitgis.ciams.enums.MenuEnum;
import com.uitgis.ciams.exception.CustomException;
import com.uitgis.ciams.model.CiamsConfig;
import com.uitgis.ciams.service.CiamsCommonService;
import com.uitgis.ciams.service.CiamsFileService;
import com.uitgis.ciams.util.ValidUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin/config")
public class AdminConfigController {
	private final CiamsFileService ciamsFileService;
	private final AdminConfigService adminConfigService;
	private final CiamsCommonService ciamsCommonService;


	@GetMapping
	public ResponseEntity<List<ConfigDto.WithFile>> list(ConfigDto.Find param) {
		List<ConfigDto.WithFile> list = adminConfigService.getLoadConfigInfo(param);
		return ResponseEntity.ok(list);
	}


	@GetMapping("/types")
	public ResponseEntity<List<ConfigDto.WithFile>> getConfigTypes() {
		List<ConfigDto.WithFile> configTypes = adminConfigService.getConfigTypeList();
		return ResponseEntity.ok(configTypes);
	}


	@GetMapping("/{id}")
	public ResponseEntity<CiamsConfig> getConfig(@PathVariable String id) throws CustomException {
		ConfigDto.WithFile ciamsConfig = adminConfigService.getConfById(id)
				.orElseThrow(() -> new CustomException("config info does not exist"));

		return ResponseEntity.ok(ciamsConfig);
	}


	@PostMapping
	public ResponseEntity<CiamsConfig> add(ConfigDto.Add param) throws Exception {

		//첨부파일 추가
		if(ValidUtil.notEmpty(param.getAttachFile())) {
			List<MultipartFile> _files = new ArrayList<>();
			_files.add(param.getAttachFile());
			ciamsFileService.uploadFiles(_files, "system", param.getId());
		}

		CiamsConfig ciamsConfig = adminConfigService.add(param);

		ciamsCommonService.log(MenuEnum.CONFIG, ActionTypeEnum.ADD, ciamsConfig.getId());

		return ResponseEntity.ok(ciamsConfig);
	}


	@PutMapping("/{id}")
	public ResponseEntity<CiamsConfig> modify(@PathVariable String id, ConfigDto.Modify param) throws Exception {

		param.setId(id);

		//이전 파일 삭제(첨부파일)
		if(ValidUtil.notEmpty(param.getRemoveFilesId())) {
			List<String> _ids = new ArrayList<>();
			_ids.add(param.getRemoveFilesId());
			ciamsFileService.deleteFilesByIds(_ids);
		}

		//첨부파일 추가
		if(ValidUtil.notEmpty(param.getAttachFile())) {
			List<MultipartFile> _files = new ArrayList<>();
			_files.add(param.getAttachFile());
			ciamsFileService.uploadFiles(_files, "system", param.getId());
		}

		CiamsConfig ciamsConfig = adminConfigService.modify(param);

		ciamsCommonService.log(MenuEnum.CONFIG, ActionTypeEnum.UPDATE, id);

		return ResponseEntity.ok(ciamsConfig);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable String id) throws Exception {
		ConfigDto.Find conTypefind = new ConfigDto.Find();
		conTypefind.setConfType(id);
		List<ConfigDto.WithFile> conTypeList = adminConfigService.getLoadConfigInfo(conTypefind);
		if(conTypeList.size() > 0) {
			throw new CustomException("하위 코드값이 존재해서 삭제 할 수 없습니다.");
		}

		ConfigDto.Find findId = new ConfigDto.Find();
		findId.setId(id);
		List<ConfigDto.WithFile> list = adminConfigService.getLoadConfigInfo(findId);
		if (list.size() == 0)
			list = adminConfigService.getConfigTypeList().stream().filter(a -> a.getId().equals(id)).collect(Collectors.toList());
		if(list.size() > 0) {
			adminConfigService.deleteById(id);

			if(ValidUtil.notEmpty(list.get(0).getAttachFile())) {
				List<String> _ids = new ArrayList<>();
				_ids.add(list.get(0).getAttachFile().getId());
				ciamsFileService.deleteFilesByIds(_ids);
			}
		}

		ciamsCommonService.log(MenuEnum.CONFIG, ActionTypeEnum.DELETE, id);

		return ResponseEntity.ok(true);
	}

}
