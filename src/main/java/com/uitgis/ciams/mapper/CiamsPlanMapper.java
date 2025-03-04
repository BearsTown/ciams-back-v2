package com.uitgis.ciams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.uitgis.ciams.dto.CiamsPlanAreaDto;
import com.uitgis.ciams.dto.CiamsPlanAreaIncenDto;
import com.uitgis.ciams.dto.CiamsPlanAreaIncenDto.Incentive;
import com.uitgis.ciams.dto.CiamsPlanAreaIncenDto.IncentiveLoc;
import com.uitgis.ciams.dto.CiamsPlanLayerDto;
import com.uitgis.ciams.model.CiamsPlanMasterSummary;
import com.uitgis.ciams.model.CiamsPlanSummary;

@Mapper
public interface CiamsPlanMapper {

	public int insertPlanArea(CiamsPlanAreaDto params);

	public int updatePlanArea(CiamsPlanAreaDto params);

	public int deletePlanArea(CiamsPlanAreaDto params);

	public int selectPlanAreaCount(CiamsPlanAreaDto.Search.Params params);

	public List<CiamsPlanAreaDto.Search.Row> selectPlanArea(CiamsPlanAreaDto.Search.Params params);

	public CiamsPlanAreaDto.Details.Result selectById(String id);

	public List<CiamsPlanAreaIncenDto.AreaIncenIncrease> selectPlanAreaIncenIncrease();

	/**
	 *
	 */

	public int insertPlanLayer(CiamsPlanLayerDto params);

	public int updatePlanLayer(CiamsPlanLayerDto params);

	public int deletePlanLayer(CiamsPlanLayerDto params);

	public List<CiamsPlanLayerDto> selectPlanLayer(CiamsPlanLayerDto params);

	/**
	 *
	 */

	public List<CiamsPlanAreaIncenDto.AreaIncen> selectPlanAreaIncen(CiamsPlanAreaIncenDto.Find.Info params);

	/**
	 * 총괄표
	 */

	public List<CiamsPlanSummary> getPlanSummary(CiamsPlanSummary params);
	public List<CiamsPlanMasterSummary> getPlanMasterSummary(CiamsPlanMasterSummary params);

	/**
	 * delete
	 */
	public int deleteIncentive(String id);

	public int deleteIncentiveLoc(String id);
}
