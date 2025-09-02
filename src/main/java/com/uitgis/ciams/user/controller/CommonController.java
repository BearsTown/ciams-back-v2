package com.uitgis.ciams.user.controller;

import com.uitgis.ciams.service.CiamsCommonService;
import com.uitgis.ciams.service.CiamsKrasService;
import com.uitgis.ciams.service.CiamsUserService;
import com.uitgis.ciams.util.CipherUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CommonController {
	private final CiamsKrasService ciamsKrasService;
	private final CiamsUserService ciamsUserService;
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

}
