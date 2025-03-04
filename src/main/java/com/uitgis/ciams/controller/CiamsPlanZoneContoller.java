package com.uitgis.ciams.controller;

import com.uitgis.ciams.service.CiamsCommonService;
import com.uitgis.ciams.service.CiamsPlanZoneService;
import com.uitgis.gis.dto.GisCiamsPlanZoneDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/plan/zone")
@RestController
public class CiamsPlanZoneContoller {

	private final CiamsPlanZoneService ciamsPlanZoneService;
	private final CiamsCommonService ciamsCommonService;


	@GetMapping("/list")
	public ResponseEntity<GisCiamsPlanZoneDTO.Search.Result> getGisCiamsPlanZoneList(GisCiamsPlanZoneDTO.Search.Params params) {
		GisCiamsPlanZoneDTO.Search.Result result = ciamsPlanZoneService.getGisCiamsPlanZoneList(params);
		return ResponseEntity.ok(result);
	}

}
