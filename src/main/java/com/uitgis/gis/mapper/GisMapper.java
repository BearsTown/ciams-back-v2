package com.uitgis.gis.mapper;

import com.uitgis.ciams.dto.CiamsMenu1StepCDetailsDto;
import com.uitgis.ciams.dto.CiamsMenu1StepCDto;
import com.uitgis.ciams.dto.CiamsMenu1StepCDto.Details.Result;
import com.uitgis.ciams.dto.CiamsMenu1StepCDto.Search.Params;
import com.uitgis.ciams.dto.CiamsMenu1StepCDto.Search.Row;
import com.uitgis.ciams.dto.Menu2ZoneDetailsDto;
import com.uitgis.ciams.dto.CiamsMonitoringDto;
import com.uitgis.ciams.dto.CiamsPlanLimitDto;
import com.uitgis.ciams.dto.CiamsPlanUpiDto;
import com.uitgis.ciams.dto.CiamsPlanUseDto;
import com.uitgis.ciams.model.CiamsPlanLImit;
import com.uitgis.ciams.model.CiamsPlanUpi;
import com.uitgis.gis.dto.CiamsF107Dto;
import com.uitgis.gis.model.CiamsF107;
import com.uitgis.gis.model.CiamsPlan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GisMapper {
    public CiamsPlanUpi selectPlanUpi(String no);

    public List<CiamsPlanUpi> selectPlanUpis(CiamsPlanUpi params);

	/* 용도지역 */
    public List<CiamsPlanUseDto.Group> getPlanUseGroup(CiamsPlanUseDto.Group params);

    /* 제척지역 */
    public List<CiamsPlanLimitDto.Group> getPlanLimitGroup(CiamsPlanLImit params);

    public List<CiamsPlanLimitDto.Group> getPlanLimitCdnmGroup(CiamsPlanLImit params);

	public int selectPlanAreaCount(Params params);

	public List<Row> getPlanAreaList(Params params);

	public Result getPlanArea(CiamsMenu1StepCDto.Search.Detail detail);

	public CiamsMenu1StepCDetailsDto.Overview.Find.Result selectOverView(CiamsMenu1StepCDetailsDto.Overview.Find.Params params);


	public Menu2ZoneDetailsDto.Overview.Find.Result selectMenu2Sub1OverView(Menu2ZoneDetailsDto.Overview.Find.Params params);




	/**
	 * 개발행위허가(지목별 카운트)
	 */
	public List<CiamsPlanUpiDto.Jimok> getPlanUpiJimok(String planId);

	/**
	 * 개발행위허가(용도지역별 카운트)
	 */
	public List<CiamsPlanUpiDto.UseArea> getPlanUpiUseArea(CiamsPlanUpiDto.UseArea.Find find);

	/**
	 * 개발행위허가(목적별 카운트)
	 */
	public List<CiamsPlanUpiDto.Purpose> getPlanUpiPurpose(CiamsPlanUpiDto.Purpose.Find find);

	/**
	 * 개발행위허가(연도별 + 목적별 카운트)
	 */
	public List<CiamsPlanUpiDto.Summary.YearPurpose> getPlanUpiSummaryYearPurpose(String planId);

	/**
	 * 개발행위허가(연도별 카운트)
	 */
	public List<CiamsPlanUpiDto.Summary.Year> getPlanUpiSummaryYear(CiamsPlanUpiDto.Summary.Year.Find find);

	/**
	 * 성장관리계획 수립현황(운영)
	 */
	public int updatePlan(CiamsPlan params);

	/**
	 * 개발행위허가(intersects) - 시군구
	 * @param search
	 * @return
	 */
	public List<CiamsF107> getF107(CiamsF107Dto.Search search);

	/**
	 * 모니터링 건축물 현황 - 행정구역 - 읍면동
	 */
	public List<CiamsMonitoringDto.BuildingTypeResult> getBuildingByDistrict(CiamsMonitoringDto.Search.District params);
	/**
	 * 모니터링 건축물 현황 - 행정구역 - 시군구
	 */
	public List<CiamsMonitoringDto.BuildingTypeResult> getBuildingBySgg(CiamsMonitoringDto.Search.District params);
	/**
	 * 모니터링 건축물 현황 - 성장관리구역
	 */
	public List<CiamsMonitoringDto.BuildingTypeResult> getBuildingByPlanarea(CiamsMonitoringDto.Search.PlanArea params);

	/**
	 * 모니터링 건축물 노후도 현황 - 행정구역
	 */
	public List<CiamsMonitoringDto.BuildingOldResult> getBuildingOldByDistrict(CiamsMonitoringDto.Search.District params);
	/**
	 * 모니터링 건축물 노후도 현황 - 성장관리구역
	 */
	public List<CiamsMonitoringDto.BuildingOldResult> getBuildingOldByPlanarea(CiamsMonitoringDto.Search.PlanArea params);

	/**
	 * 모니터링 필지 지목별 현황 - 행정구역
	 */
	public List<CiamsMonitoringDto.CommonResult> getParcelTypeByDistrict(CiamsMonitoringDto.Search.Parcel params);
	/**
	 * 모니터링 필지 소유자별 현황 - 행정구역
	 */
	public List<CiamsMonitoringDto.CommonResult> getParcelOwnerByDistrict(CiamsMonitoringDto.Search.Parcel params);

}
