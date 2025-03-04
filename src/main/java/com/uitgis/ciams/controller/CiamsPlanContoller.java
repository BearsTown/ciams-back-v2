package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.CiamsMenu1StepCDto;
import com.uitgis.ciams.dto.CiamsPlanAreaIncenDto;
import com.uitgis.ciams.dto.CiamsPlanLayerDto;
import com.uitgis.ciams.dto.CiamsPlanLimitDto;
import com.uitgis.ciams.dto.CiamsPlanMapLayerDto;
import com.uitgis.ciams.dto.CiamsPlanMapServiceDto;
import com.uitgis.ciams.dto.CiamsPlanUseDto;
import com.uitgis.ciams.enums.ActionTypeEnum;
import com.uitgis.ciams.enums.MenuEnum;
import com.uitgis.ciams.model.CiamsPlanAreaLink;
import com.uitgis.ciams.model.CiamsPlanLImit;
import com.uitgis.ciams.model.CiamsPlanMasterSummary;
import com.uitgis.ciams.model.CiamsPlanSummary;
import com.uitgis.ciams.service.CiamsCommonService;
import com.uitgis.ciams.service.CiamsPlanZoneService;
import com.uitgis.gis.dto.CiamsF107Dto;
import com.uitgis.gis.model.CiamsF107;
import com.uitgis.gis.model.CiamsPlan;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/plan")
@RestController
public class CiamsPlanContoller {

	private final CiamsPlanZoneService ciamsPlanZoneService;
	private final CiamsCommonService ciamsCommonService;

	@GetMapping("/use/getGroup")
	public ResponseEntity<?> getuseGroup(CiamsPlanUseDto.Group params) {
		try {
			List<CiamsPlanUseDto.Group> result = ciamsPlanZoneService.getuseGroup(params);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.ok(false);
		}
	}

	/**
	 *
	 */

	@GetMapping("/layer/get")
	public ResponseEntity<?> getLayer(CiamsPlanLayerDto params) {
		List<CiamsPlanLayerDto> result = ciamsPlanZoneService.selectPlanLayer(params);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/layer/limit/get")
	public ResponseEntity<?> getLimitLayer(CiamsPlanLImit params) {
		List<CiamsPlanLimitDto.Group> result = ciamsPlanZoneService.selectPlanLimitLayer(params);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/layer/limitCdnm/get")
	public ResponseEntity<?> getCdnmLimitLayer(CiamsPlanLImit params) {
		List<CiamsPlanLimitDto.Group> result = ciamsPlanZoneService.selectPlanLimitCdnmLayer(params);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/layer/post")
	public ResponseEntity<?> insert(@RequestBody CiamsPlanLayerDto params) {
		int count = 0;
		try {
			count = ciamsPlanZoneService.insertPlanLayer(params);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		ciamsCommonService.log(MenuEnum.PLAN_LAYER, ActionTypeEnum.ADD, params.getPlanLayerId());

		return ResponseEntity.ok(count > 0 ? true : false);
	}

	@PutMapping("/layer/put")
	public ResponseEntity<?> update(@RequestBody CiamsPlanLayerDto params) {
		int count = 0;
		Map<String, Object> result = new HashMap<>();
		result.put("success", false);

		if (params.getPlanLayerId() == null) {
			result.put("message", "planLayerId is null.");
			return ResponseEntity.ok(result);
		}

		try {
			count = ciamsPlanZoneService.updatePlanLayer(params);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		if (count == 0) {
			result.put("message", "0 updates.");
			return ResponseEntity.ok(result);
		}

		ciamsCommonService.log(MenuEnum.PLAN_LAYER, ActionTypeEnum.UPDATE, params.getPlanLayerId());

		result.put("success", true);

		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/layer/delete/{layerId}")
	public ResponseEntity<?> delete(@PathVariable String layerId) {
		int count = 0;

		CiamsPlanLayerDto params = new CiamsPlanLayerDto();
		params.setPlanLayerId(layerId);

		try {
			count = ciamsPlanZoneService.deletePlanLayer(params);

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		ciamsCommonService.log(MenuEnum.PLAN_LAYER, ActionTypeEnum.DELETE, layerId);

		return ResponseEntity.ok(count > 0 ? true : false);
	}

	/**
	 *
	 */

	@GetMapping("/area/list")
	public ResponseEntity<?> getPlanAreaList(CiamsMenu1StepCDto.Search.Params params) {
		CiamsMenu1StepCDto.Search.Result result = ciamsPlanZoneService.getPlanAreaList(params);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/area/get/{planId}/{planAreaId}")
	public ResponseEntity<?> getPlanArea(@PathVariable("planId") String planId, @PathVariable("planAreaId") int planAreaId) {
		CiamsMenu1StepCDto.Search.Detail params = new CiamsMenu1StepCDto.Search.Detail();
		params.setPlanId(planId);
//		params.setPlanAreaId(planAreaId);
		CiamsMenu1StepCDto.Details.Result result = ciamsPlanZoneService.getPlanArea(params);
		return ResponseEntity.ok(result);
	}

	@GetMapping("{id}/areaLink")
	public ResponseEntity<?> getAreaLink(@PathVariable("id") String id) {
		List<CiamsPlanAreaLink> result = ciamsPlanZoneService.selectPlanAreaLink(id);
		return ResponseEntity.ok(result);
	}

	/**
	 *
	 */

	@GetMapping("/incen/get")
	public ResponseEntity<?> getPlanAreaIncen(CiamsPlanAreaIncenDto.Find.Info params) {
		CiamsPlanAreaIncenDto.AreaTypeItems result = ciamsPlanZoneService.selectPlanAreaIncen(params);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/increase/list")
	public ResponseEntity<?> getPlanAreaIncenIncrease() {
		List<CiamsPlanAreaIncenDto.AreaIncenIncrease> result = ciamsPlanZoneService.selectPlanAreaIncenIncrease();
		return ResponseEntity.ok(result);
	}

	/*
	 * @GetMapping("/incentive/update") public ResponseEntity<?>
	 * updateIncenJob(CiamsPlanAreaIncenDto.Find.Info params) {
	 *
	 * int count = 0; Map<String, Object> result = new HashMap<>();
	 * result.put("success", false);
	 *
	 * try { count = ciamsPlanService.updateIncentive(params); } catch (Exception e)
	 * { e.printStackTrace(); log.error(e.getMessage()); }
	 *
	 * if (count == 0) { result.put("message", "0 updates."); return
	 * ResponseEntity.ok(result); }
	 *
	 * result.put("success", true); return ResponseEntity.ok(result);
	 *
	 * }
	 */

	@GetMapping("/MapService/list")
	public ResponseEntity<?> getMapService(CiamsPlanMapServiceDto params) {
		// params.setPlanId(params.getPlanId().toUpperCase());
		List<CiamsPlanMapServiceDto> result = ciamsPlanZoneService.getMapService(params);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/MapLayers/list")
	public ResponseEntity<?> getMapLayers(CiamsPlanMapLayerDto params) {
		List<CiamsPlanMapLayerDto> result = ciamsPlanZoneService.getMapLayers(params);
		return ResponseEntity.ok(result);
	}

	/**
	 * 총괄표
	 */

	@GetMapping("/summary/{planId}/get")
	public ResponseEntity<?> getSummary(@PathVariable String planId) {
		CiamsPlanSummary params = new CiamsPlanSummary();
		params.setPlanId(planId);

		List<CiamsPlanSummary> result = ciamsPlanZoneService.getPlanSummary(params);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/mastersummary/{planId}/get")
	public ResponseEntity<?> getMasterSummary(@PathVariable String planId) {
		CiamsPlanMasterSummary params = new CiamsPlanMasterSummary();
		params.setPlanId(planId);

		List<CiamsPlanMasterSummary> result = ciamsPlanZoneService.getPlanMaterSummary(params);
		return ResponseEntity.ok(result);
	}

	@PutMapping("/gis")
	public ResponseEntity<?> updateGis(@RequestBody CiamsPlan params) {
		int resCnt = 0;

		resCnt = ciamsPlanZoneService.updateGis(params);

		ciamsCommonService.log(MenuEnum.GIS, ActionTypeEnum.UPDATE, params.getPlanId());

		return ResponseEntity.ok(resCnt > 0 ? true : false);
	}

	@GetMapping("/intersects/f107")
	public ResponseEntity<?> getF107(CiamsF107Dto.Search params) {

		List<CiamsF107> result = ciamsPlanZoneService.getF107(params);
		return ResponseEntity.ok(result);
	}
}
