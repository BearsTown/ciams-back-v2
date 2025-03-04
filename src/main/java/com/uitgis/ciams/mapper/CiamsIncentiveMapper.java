package com.uitgis.ciams.mapper;

import com.uitgis.ciams.dto.CiamsIncentiveDetailsDto;
import com.uitgis.ciams.dto.CiamsIncentiveDto;
import com.uitgis.ciams.dto.IncentiveCalculateDto;
import com.uitgis.ciams.dto.IncentiveCheckListDto;
import com.uitgis.ciams.model.CiamsCode;
import com.uitgis.ciams.model.CiamsIncentiveCheckList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CiamsIncentiveMapper {
	/**
	 * 인센티브 검색 결과 조회
	 */
	public List<CiamsIncentiveDto.Search.Find.IncenSearchList> getIncentiveList(CiamsIncentiveDto.Search.Find.Params params);
	public int getIncentiveTotal(CiamsIncentiveDto.Search.Find.Params params);

	public CiamsIncentiveDto.TargetLand.Find.Result selectTargetLand(CiamsIncentiveDto.TargetLand.Find.Params params);

	public int insertIncentive(CiamsIncentiveDto.Add params);

	public int insertIncentiveLocs(CiamsIncentiveDto.Loc params);

	public int updateIncentive(CiamsIncentiveDto.Add params);

	public int updateIncentiveLocs(CiamsIncentiveDto.Loc params);

	public int deleteIncentive(String incentiveId);

	public int deleteIncentiveLoc(String incentiveLocId);

    /**
     * 인센티브 현황 상세정보 개요
     */
    public CiamsIncentiveDetailsDto.Overview.Find.Result selectIncentiveOverview(CiamsIncentiveDetailsDto.Overview.Find.Params params);

    public List<CiamsCode> selectAreaUseCdList(CiamsIncentiveDetailsDto.Overview.Find.Params params);

    /**
     *
     */
    public CiamsIncentiveCheckList selectIncentiveCheckListById(String id);

    public List<IncentiveCheckListDto.Row> selectIncentiveCheckList(IncentiveCheckListDto.Find.Params params);

    public int insertIncentiveCheckList(IncentiveCheckListDto.Insert data);

    public int updateIncentiveCheckList(IncentiveCheckListDto.Insert data);

    public int deleteIncentiveCheckListById(IncentiveCheckListDto.Delete id);

    /**
     * 인센티브 정보
     */
    public CiamsIncentiveDetailsDto.IncentiveInfo.Find.Result selectIncentiveCalcById(String calcId);

    public List<CiamsIncentiveDetailsDto.AreaSubject.Find.Result> selectIncentiveLoc(CiamsIncentiveDetailsDto.AreaSubject.Find.Params params);

    public List<CiamsIncentiveDetailsDto.IncentiveInfo.Find.Result> selectIncentiveCalc(CiamsIncentiveDetailsDto.IncentiveInfo.Find.Params params);

    /**
     * 인센티브 산정
     */
    public IncentiveCalculateDto.Details selectIncentiveCalculateDetails(String incentiveId);

    public List<IncentiveCalculateDto.Props> selectIncentiveProps(IncentiveCalculateDto.Find.Params.Props params);

    public IncentiveCalculateDto.AreaIncentive selectIncentiveCalculateAreas(IncentiveCalculateDto.Find.Params.Info params);

    public int insertIncentiveCalculate(IncentiveCalculateDto.Insert data);

    public int updateIncentiveCalculate(IncentiveCalculateDto.Insert data);

    public int updateIncentiveCalculateStateById(IncentiveCalculateDto.Update params);

    public int updateStateDisableAllById(String calcId);

    public int deleteIncentiveCalcById(IncentiveCalculateDto.Delete params);

    /**
     *
     */
    public List<String> selectGroupByNo();

    /**
     * 모니터링 차트데이터(인센티브현황)
     * @return
     */
	public List<CiamsIncentiveDetailsDto.chartGroupData> selectGroupByJimok(@Param("pnuList") List<String> pnuList);
	public List<CiamsIncentiveDetailsDto.chartGroupData> selectGroupByAreaUse(@Param("pnuList") List<String> pnuList);
	public List<CiamsIncentiveDetailsDto.chartGroupData> selectGroupByYear(@Param("pnuList") List<String> pnuList);
	public List<CiamsIncentiveDetailsDto.chartGeometryData> selectGeometrys(@Param("pnuList") List<String> pnuList);

    /**
     *
     */
    public List<CiamsIncentiveDto.PNUIncentive> selectPNUIncentive(String pnu);
}
