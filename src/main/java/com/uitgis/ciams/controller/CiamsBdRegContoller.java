package com.uitgis.ciams.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uitgis.ciams.dto.CiamsBdEtcDto;
import com.uitgis.ciams.dto.CiamsBdRegDto;
import com.uitgis.ciams.service.CiamsBdEtcService;
import com.uitgis.ciams.service.CiamsBdRegService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/plan")
@RestController
public class CiamsBdRegContoller {

	private final CiamsBdRegService ciamsBdRegService;
	private final CiamsBdEtcService ciamsBdEtcService;

	@GetMapping("/bd/reg/get")
	public ResponseEntity<?> getLayer(@RequestParam(required = false) String pnu, CiamsBdRegDto.Search.param params) {

		Map<String, Object> result = new HashMap<>();
		if (pnu == null || pnu.trim().equals("")) {
			result.put("success", false);
			result.put("message", "pnu is required.");
			return ResponseEntity.ok(result);
		} else {
			params.setPnu(pnu);
			List<CiamsBdRegDto.Search.Row> lists = ciamsBdRegService.selectBdReg(params);
			result.put("success", true);
			result.put("data", lists);
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/bd/etc/get")
	public ResponseEntity<?> getEtcInfo(@RequestParam(required = false) String pnu, CiamsBdEtcDto.Search.param params) {

		Map<String, Object> result = new HashMap<>();
		if (pnu == null || pnu.trim().equals("")) {
			result.put("success", false);
			result.put("message", "pnu is required.");
			return ResponseEntity.ok(result);
		} else {
			params.setPnu(pnu);
			List<CiamsBdEtcDto.Search.Row> lists = ciamsBdEtcService.selectBdEtc(params);
			result.put("success", true);
			result.put("data", lists);
		}
		return ResponseEntity.ok(result);
	}

}
