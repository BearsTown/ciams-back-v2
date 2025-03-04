package com.uitgis.ciams.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.uitgis.ciams.dto.CiamsMenu3Sub1DetailsDto;
import com.uitgis.ciams.dto.CiamsPlanZoneDto;
import com.uitgis.ciams.service.CiamsPlanZoneTestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uitgis.ciams.dto.CiamsConfigDto;
import com.uitgis.ciams.enums.ActionTypeEnum;
import com.uitgis.ciams.enums.MenuEnum;
import com.uitgis.ciams.exception.CustomException;
import com.uitgis.ciams.model.CiamsConfig;
import com.uitgis.ciams.service.CiamsCommonService;
import com.uitgis.ciams.service.CiamsConfigService;
import com.uitgis.ciams.service.CiamsFileService;
import com.uitgis.ciams.util.ValidUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/config")
public class CiamsConfigController {

	private final CiamsPlanZoneTestService ciamsPlanZoneTestService;

	private final CiamsConfigService ciamsConfigService;
	private final CiamsFileService ciamsFileService;
	private final CiamsCommonService ciamsCommonService;

	@GetMapping("/test")
	public ResponseEntity<CiamsMenu3Sub1DetailsDto.Overview.Find.Result> test(CiamsMenu3Sub1DetailsDto.Overview.Find.Params params) {
		CiamsMenu3Sub1DetailsDto.Overview.Find.Result result = ciamsPlanZoneTestService.getMenu3Sub1OverView(params);
		return ResponseEntity.ok(result);
	}

	@GetMapping
	public ResponseEntity<List<CiamsConfigDto.WithFile>> list(CiamsConfigDto.Find param) {

		List<CiamsConfigDto.WithFile> list = ciamsConfigService.getLoadConfigInfo(param);
		return ResponseEntity.ok(list);
	}

	@GetMapping("types")
	public ResponseEntity<List<CiamsConfigDto.WithFile>> getConfigTypes(){
		List<CiamsConfigDto.WithFile> configTypes= ciamsConfigService.getConfigTypeList();
		return ResponseEntity.ok(configTypes);
	}

	@GetMapping("{id}")
	public ResponseEntity<CiamsConfig> getConfig(@PathVariable String id) throws CustomException {

		CiamsConfigDto.WithFile ciamsConfig = ciamsConfigService.getConfById(id)
				.orElseThrow(() -> new CustomException("config info does not exist"));

		return ResponseEntity.ok(ciamsConfig);
	}

	@PostMapping
	public ResponseEntity<CiamsConfig> add(CiamsConfigDto.Add param) throws Exception {

		//첨부파일 추가
		if(ValidUtil.notEmpty(param.getAttachFile())) {
			List<MultipartFile> _files = new ArrayList<>();
			_files.add(param.getAttachFile());
			ciamsFileService.uploadFiles(_files, "system", param.getId());
		}

		CiamsConfig ciamsConfig = ciamsConfigService.add(param);

		ciamsCommonService.log(MenuEnum.CONFIG, ActionTypeEnum.ADD, ciamsConfig.getId());

		return ResponseEntity.ok(ciamsConfig);
	}

	@PutMapping("{id}")
	public ResponseEntity<CiamsConfig> modify(@PathVariable String id, CiamsConfigDto.Modify param) throws Exception {

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

		CiamsConfig ciamsConfig = ciamsConfigService.modify(param);

		ciamsCommonService.log(MenuEnum.CONFIG, ActionTypeEnum.UPDATE, id);

		return ResponseEntity.ok(ciamsConfig);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> remove(@PathVariable String id) throws Exception {
		CiamsConfigDto.Find conTypefind = new CiamsConfigDto.Find();
		conTypefind.setConfType(id);
		List<CiamsConfigDto.WithFile> conTypeList = ciamsConfigService.getLoadConfigInfo(conTypefind);
		if(conTypeList.size() > 0) {
			throw new CustomException("하위 코드값이 존재해서 삭제 할 수 없습니다.");
		}

		CiamsConfigDto.Find findId = new CiamsConfigDto.Find();
		findId.setId(id);
		List<CiamsConfigDto.WithFile> list = ciamsConfigService.getLoadConfigInfo(findId);
		if(list.size() == 0) list = ciamsConfigService.getConfigTypeList().stream().filter(a-> a.getId().equals(id)).collect(Collectors.toList());
		if(list.size() > 0) {
			ciamsConfigService.deleteById(id);

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
