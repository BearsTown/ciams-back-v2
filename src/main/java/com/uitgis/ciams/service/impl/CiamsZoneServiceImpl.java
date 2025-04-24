package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.CiamsMenu1StepCDto;
import com.uitgis.ciams.dto.CiamsPlanAreaDto;
import com.uitgis.ciams.dto.CiamsPlanAreaIncenDto;
import com.uitgis.ciams.dto.CiamsPlanLayerDto;
import com.uitgis.ciams.dto.CiamsPlanLimitDto;
import com.uitgis.ciams.dto.CiamsPlanMapLayerDto;
import com.uitgis.ciams.dto.CiamsPlanMapServiceDto;
import com.uitgis.ciams.dto.CiamsPlanUseDto;
import com.uitgis.ciams.dto.PaginationDto;
import com.uitgis.ciams.mapper.CiamsPlanAreaLinkMapper;
import com.uitgis.ciams.mapper.CiamsPlanMapper;
import com.uitgis.ciams.model.CiamsPlanAreaLink;
import com.uitgis.ciams.model.CiamsPlanLImit;
import com.uitgis.ciams.model.CiamsPlanMasterSummary;
import com.uitgis.ciams.model.CiamsPlanSummary;
import com.uitgis.ciams.service.CiamsZoneService;
import com.uitgis.ciams.util.PageUtil;
import com.uitgis.gis.dto.CiamsF107Dto.Search;
import com.uitgis.gis.dto.GisCiamsZoneDTO;
import com.uitgis.gis.mapper.GisCiamsZoneMapper;
import com.uitgis.gis.mapper.GisMapper;
import com.uitgis.gis.model.CiamsF107;
import com.uitgis.gis.model.CiamsPlan;
import com.uitgis.mis.mapper.MisMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsZoneServiceImpl implements CiamsZoneService {
	private final GisMapper gismapper;
	private final GisCiamsZoneMapper gisCiamsZoneMapper;
	private final MisMapper mismapper;
	private final CiamsPlanMapper ciamsplanmapper;
	private final CiamsPlanAreaLinkMapper ciamsPlanAreaLinkMapper;

	@Override
	public List<CiamsPlanUseDto.Group> getuseGroup(CiamsPlanUseDto.Group params) {
		return gismapper.getPlanUseGroup(params);
	}

	/**
	 *
	 */

	@Override
	public int insertPlanArea(CiamsPlanAreaDto params) throws Exception {
		return ciamsplanmapper.insertPlanArea(params);
	}

	@Override
	public int updatePlanArea(CiamsPlanAreaDto params) throws Exception {
		return ciamsplanmapper.updatePlanArea(params);
	}

	@Override
	public int deletePlanArea(CiamsPlanAreaDto params) throws Exception {
		return ciamsplanmapper.deletePlanArea(params);
	}

	@Override
	public int selectPlanAreaCount(CiamsMenu1StepCDto.Search.Params params) {
		return gismapper.selectPlanAreaCount(params);
	}

//	@Override
//	public CiamsMenu1StepCDto.Search.Result getPlanAreaList(CiamsMenu1StepCDto.Search.Params params) {
//		int totalCount = gismapper.selectPlanAreaCount(params);
//
//		PaginationDto page = PageUtil.setTotalCount(params, totalCount);
//
//		List<CiamsMenu1StepCDto.Search.Row> rows = gismapper.getPlanAreaList(params);
//
//		CiamsMenu1StepCDto.Search.Result result = CiamsMenu1StepCDto.Search.Result.builder().page(page).list(rows).build();
//
//		return result;
//	}

	@Override
	public CiamsMenu1StepCDto.Details.Result getPlanArea(CiamsMenu1StepCDto.Search.Detail detail) {
		return gismapper.getPlanArea(detail);
	}

	@Override
	public List<CiamsPlanAreaIncenDto.AreaIncenIncrease> selectPlanAreaIncenIncrease() {
		return ciamsplanmapper.selectPlanAreaIncenIncrease();
	}

	@Override
	public List<CiamsPlanAreaLink> selectPlanAreaLink(String planAreaId) {
		return ciamsPlanAreaLinkMapper.selectByAreaId(planAreaId);
	}

	/**
	 *
	 */

	@Override
	public int insertPlanLayer(CiamsPlanLayerDto params) throws Exception {
		return ciamsplanmapper.insertPlanLayer(params);
	}

	@Override
	public int updatePlanLayer(CiamsPlanLayerDto params) throws Exception {
		return ciamsplanmapper.updatePlanLayer(params);
	}

	@Override
	public int deletePlanLayer(CiamsPlanLayerDto params) throws Exception {
		return ciamsplanmapper.deletePlanLayer(params);
	}

	@Override
	public List<CiamsPlanLayerDto> selectPlanLayer(CiamsPlanLayerDto params) {
		return ciamsplanmapper.selectPlanLayer(params);
	}

	/**
	 *
	 */

	@Override
	@Transactional(readOnly = true)
	public CiamsPlanAreaIncenDto.AreaTypeItems selectPlanAreaIncen(CiamsPlanAreaIncenDto.Find.Info params) {
		CiamsPlanAreaIncenDto.AreaTypeItems items = new CiamsPlanAreaIncenDto.AreaTypeItems();

		params.setAreaType("bcr");
		items.setBcrItems(ciamsplanmapper.selectPlanAreaIncen(params));

		params.setAreaType("far");
		items.setFarItems(ciamsplanmapper.selectPlanAreaIncen(params));

		return items;
	}

	@Override
	public List<CiamsPlanLimitDto.Group> selectPlanLimitLayer(CiamsPlanLImit params) {
		return gismapper.getPlanLimitGroup(params);
	}

	@Override
	public List<CiamsPlanLimitDto.Group> selectPlanLimitCdnmLayer(CiamsPlanLImit params) {
		return gismapper.getPlanLimitCdnmGroup(params);
	}

	@Override
	public List<CiamsPlanMapServiceDto> getMapService(CiamsPlanMapServiceDto params) {
		return mismapper.getMapService(params);
	}

	@Override
	public List<CiamsPlanMapLayerDto> getMapLayers(CiamsPlanMapLayerDto params) {
		return mismapper.getMapLayers(params);
	}

	/**
	 * 총괄표
	 */

	@Override
	public List<CiamsPlanSummary> getPlanSummary(CiamsPlanSummary params) {
		return ciamsplanmapper.getPlanSummary(params);
	}

	@Override
	public List<CiamsPlanMasterSummary> getPlanMaterSummary(CiamsPlanMasterSummary params) {
		return ciamsplanmapper.getPlanMasterSummary(params);
	}

	@Override
	public int updateGis(CiamsPlan params) {
		return gismapper.updatePlan(params);
	}

	@Override
	public List<CiamsF107> getF107(Search params) {
		return gismapper.getF107(params);
	}


	/**
	 *
	 *
	 *
	 */
	@Override
	public GisCiamsZoneDTO.Search.Result getCiamsZoneList(GisCiamsZoneDTO.Search.Params params) {
		int totalCount = gisCiamsZoneMapper.selectGisCiamsZoneCount(params);

		PaginationDto page = PageUtil.setTotalCount(params, totalCount);

		List<GisCiamsZoneDTO.Search.Row> rows = gisCiamsZoneMapper.selectGisCiamsZoneList(params);

		GisCiamsZoneDTO.Search.Result result = GisCiamsZoneDTO.Search.Result.builder()
				.page(page)
				.list(rows)
				.build();

		return result;
	}

}
