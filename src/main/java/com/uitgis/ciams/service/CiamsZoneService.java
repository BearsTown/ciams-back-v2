package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.CiamsMenu1StepCDto;
import com.uitgis.ciams.dto.CiamsPlanAreaDto;
import com.uitgis.ciams.dto.CiamsPlanAreaIncenDto;
import com.uitgis.ciams.dto.CiamsPlanLayerDto;
import com.uitgis.ciams.dto.CiamsPlanLimitDto;
import com.uitgis.ciams.dto.CiamsPlanMapLayerDto;
import com.uitgis.ciams.dto.CiamsPlanMapServiceDto;
import com.uitgis.ciams.dto.CiamsPlanUseDto;
import com.uitgis.ciams.model.CiamsPlanAreaLink;
import com.uitgis.ciams.model.CiamsPlanLImit;
import com.uitgis.ciams.model.CiamsPlanMasterSummary;
import com.uitgis.ciams.model.CiamsPlanSummary;
import com.uitgis.gis.dto.CiamsF107Dto.Search;
import com.uitgis.gis.dto.GisCiamsZoneDTO;
import com.uitgis.gis.model.CiamsF107;
import com.uitgis.gis.model.CiamsPlan;

import java.util.List;

public interface CiamsZoneService {
	public List<CiamsPlanUseDto.Group> getuseGroup(CiamsPlanUseDto.Group params);

	/**
	 *
	 */

	public int insertPlanArea(CiamsPlanAreaDto params) throws Exception;

	public int updatePlanArea(CiamsPlanAreaDto params) throws Exception;

	public int deletePlanArea(CiamsPlanAreaDto params) throws Exception;

	public int selectPlanAreaCount(CiamsMenu1StepCDto.Search.Params params);

//	public CiamsMenu1StepCDto.Search.Result getPlanAreaList(CiamsMenu1StepCDto.Search.Params params);

	public List<CiamsPlanAreaLink> selectPlanAreaLink(String planAreaId);

	public CiamsMenu1StepCDto.Details.Result getPlanArea(CiamsMenu1StepCDto.Search.Detail detail);

	public List<CiamsPlanAreaIncenDto.AreaIncenIncrease> selectPlanAreaIncenIncrease();

	/**
	 *
	 */

	public int insertPlanLayer(CiamsPlanLayerDto params) throws Exception;

	public int updatePlanLayer(CiamsPlanLayerDto params) throws Exception;

	public int deletePlanLayer(CiamsPlanLayerDto params) throws Exception;

	public List<CiamsPlanLayerDto> selectPlanLayer(CiamsPlanLayerDto params);

	public List<CiamsPlanLimitDto.Group> selectPlanLimitLayer(CiamsPlanLImit params);

	public List<CiamsPlanLimitDto.Group> selectPlanLimitCdnmLayer(CiamsPlanLImit params);


	/**
	 *
	 */
	public CiamsPlanAreaIncenDto.AreaTypeItems selectPlanAreaIncen(CiamsPlanAreaIncenDto.Find.Info params);

	public List<CiamsPlanMapServiceDto> getMapService(CiamsPlanMapServiceDto params);
	public List<CiamsPlanMapLayerDto> getMapLayers(CiamsPlanMapLayerDto params);

	/**
	 * 총괄표
	 */

	public List<CiamsPlanSummary> getPlanSummary(CiamsPlanSummary params);
	public List<CiamsPlanMasterSummary> getPlanMaterSummary(CiamsPlanMasterSummary params);

	/***/
	public int updateGis(CiamsPlan params);

	/**
	 * 개발행위허가(intersects - 시군구)
	 * @param params
	 */
	public List<CiamsF107> getF107(Search params);


	/**
	 *
	 *
	 *
	 *
	 *
	 */

	public GisCiamsZoneDTO.Search.Result getCiamsZoneList(GisCiamsZoneDTO.Search.Params params);

}
