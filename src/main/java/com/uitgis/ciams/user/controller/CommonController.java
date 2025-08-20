package com.uitgis.ciams.user.controller;

import com.uitgis.ciams.user.dto.CiamsSsoUserDto;
import com.uitgis.ciams.enums.ActionTypeEnum;
import com.uitgis.ciams.enums.MenuEnum;
import com.uitgis.ciams.service.CiamsCommonService;
import com.uitgis.ciams.service.CiamsKrasService;
import com.uitgis.ciams.service.CiamsSsoUserService;
import com.uitgis.ciams.util.CipherUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CommonController {
	private final CiamsKrasService ciamsKrasService;
	private final CiamsSsoUserService ciamsSsoUserService;
	private final CiamsCommonService ciamsCommonService;

	private boolean krasLoading;


	@GetMapping("/kras")
	public ResponseEntity<?> renewalKrasData() throws Exception {
		if (krasLoading) return ResponseEntity.ok("KRAS DATA LOADING...");
		log.info("Renewal Kras Data Start");
		krasLoading = true;
		ciamsKrasService.renewalKrasData();
		krasLoading = false;
		log.info("Renewal Kras Data End");
		return ResponseEntity.ok("KRAS DATA END");
	}


	@GetMapping("/rsa/key")
	public ResponseEntity<String> getRsaPublicKey() {
		return ResponseEntity.ok(CipherUtil.getPublicKey());
	}


	@PostMapping("/cmm/signUp")
	public ResponseEntity<?> addUser(@RequestBody CiamsSsoUserDto.Add dto) throws Exception{

		try {
			ciamsSsoUserService.addUser(dto);

			ciamsCommonService.log(MenuEnum.USER, ActionTypeEnum.ADD, dto.getId());
		}catch (DuplicateKeyException e) {
			return new ResponseEntity<>("로그인ID중복입니다.", HttpStatus.CONFLICT);
		}

		return ResponseEntity.ok().build();
	}

}
