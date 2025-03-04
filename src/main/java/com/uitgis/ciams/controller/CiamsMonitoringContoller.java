package com.uitgis.ciams.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uitgis.ciams.dto.CiamsMonitoringDto;
import com.uitgis.ciams.service.CiamsMonitoringService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/monitoring")
@RestController
public class CiamsMonitoringContoller {

	private final CiamsMonitoringService ciamsMonitoringService;

	@GetMapping("/getBuildingByDistrict")
	public ResponseEntity<?> getBuildingByDistrict(CiamsMonitoringDto.Search.District params) {

		List<CiamsMonitoringDto.BuildingTypeResult> result;

		try {
			if (params.getEmdCd() == null) {
				result = ciamsMonitoringService.getBuildingBySgg(params);
			} else {
				params.setEmdCds(Arrays.asList(params.getEmdCd().split(",")));
				result = ciamsMonitoringService.getBuildingByDistrict(params);
			}
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.ok(false);
		}
	}

	@GetMapping("/getBuildingByPlanarea")
	public ResponseEntity<?> getBuildingByPlanarea(CiamsMonitoringDto.Search.PlanArea params) {
		try {
			if(params.getAreaId() != null) {
				params.setAreaIds(Arrays.asList(params.getAreaId().split(",")));
			}
			List<CiamsMonitoringDto.BuildingTypeResult> result = ciamsMonitoringService.getBuildingByPlanarea(params);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.ok(false);
		}
	}
	
	@GetMapping("/getBuildingOldByDistrict")
	public ResponseEntity<?> getBuildingOldByDistrict(CiamsMonitoringDto.Search.District params) {
		try {
			
			if (params.getYear() == 0) {
				return ResponseEntity.ok("year parameter required.");
			}
			
			if (params.getEmdCd() != null) {				
				params.setEmdCds(Arrays.asList(params.getEmdCd().split(",")));
			}
			List<CiamsMonitoringDto.BuildingOldResult>result = ciamsMonitoringService.getBuildingOldByDistrict(params);			
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.ok(false);
		}
	}

	@GetMapping("/getBuildingOldByPlanarea")
	public ResponseEntity<?> getBuildingOldByPlanarea(CiamsMonitoringDto.Search.PlanArea params) {
		try {
			
			if (params.getYear() == 0) {
				return ResponseEntity.ok("year parameter required.");
			}
			
			if(params.getAreaId() != null) {
				params.setAreaIds(Arrays.asList(params.getAreaId().split(",")));
			}
			List<CiamsMonitoringDto.BuildingOldResult> result = ciamsMonitoringService.getBuildingOldByPlanarea(params);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.ok(false);
		}
	}
	
	@GetMapping("/getParcelTypeByDistrict")
	public ResponseEntity<?> getParcelTypeByDistrict(CiamsMonitoringDto.Search.Parcel params) {

		List<CiamsMonitoringDto.CommonResult> result;

		try {
			if (params.getEmdCd() == null) {
				result = ciamsMonitoringService.getParcelTypeByDistrict(params);
			} else {
				params.setEmdCds(Arrays.asList(params.getEmdCd().split(",")));
				result = ciamsMonitoringService.getParcelTypeByDistrict(params);
			}
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.ok(false);
		}
	}
	
	@GetMapping("/getParcelOwnerByDistrict")
	public ResponseEntity<?> getParcelOwnerByDistrict(CiamsMonitoringDto.Search.Parcel params) {

		List<CiamsMonitoringDto.CommonResult> result;

		try {
			if (params.getEmdCd() == null) {
				result = ciamsMonitoringService.getParcelOwnerByDistrict(params);
			} else {
				params.setEmdCds(Arrays.asList(params.getEmdCd().split(",")));
				result = ciamsMonitoringService.getParcelOwnerByDistrict(params);
			}
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.ok(false);
		}
	}

}
