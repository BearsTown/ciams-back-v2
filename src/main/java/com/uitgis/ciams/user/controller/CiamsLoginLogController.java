package com.uitgis.ciams.user.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uitgis.ciams.user.dto.CiamsLoginLogDto;
import com.uitgis.ciams.admin.service.CiamsLoginLogService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/v1/log")
@RestController
@AllArgsConstructor
public class CiamsLoginLogController {

	final private CiamsLoginLogService loginLogService;

	@GetMapping("/list")
	public ResponseEntity<?> list(CiamsLoginLogDto.Find find) throws Exception{
		Map<String, Object> list = loginLogService.selectList(find);
		return ResponseEntity.ok(list);
	}
}
