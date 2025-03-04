package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.CiamsIncentiveDetailsDto;
import com.uitgis.ciams.dto.CiamsIncentiveDto;
import com.uitgis.ciams.dto.IncentiveCalculateDto;
import com.uitgis.ciams.dto.IncentiveCheckListDto;
import com.uitgis.ciams.exception.CustomException;
import com.uitgis.ciams.model.CiamsFile;
import com.uitgis.ciams.model.CiamsIncentiveCheckList;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CiamsIncentiveService {
	/**
	 * 인센티브 검색 결과 조회
	 */
	public Map<String, Object> getIncentiveList(CiamsIncentiveDto.Search.Find.Params params);

	public CiamsIncentiveDto.TargetLand.Find.Result selectTargetLand(CiamsIncentiveDto.TargetLand.Find.Params params);

    public int insertIncentive(CiamsIncentiveDto.Add params) throws Exception;

    public int updateIncentive(CiamsIncentiveDto.Add params) throws Exception;

    public int deleteIncentive(String incentiveId) throws Exception;

    public int deleteIncentiveLoc(String incentiveLocId) throws Exception;

    /**
     *
     */
    public CiamsIncentiveDetailsDto.Overview.Find.Result getIncentiveOverview(CiamsIncentiveDetailsDto.Overview.Find.Params params);

    /**
     *
     */
    public CiamsIncentiveCheckList selectIncentiveCheckListById(String id);

    public List<IncentiveCheckListDto.Row> selectIncentiveCheckList(IncentiveCheckListDto.Find.Params params);

    public String insertIncentiveCheckList(IncentiveCheckListDto.Insert data);

    public String updateIncentiveCheckList(IncentiveCheckListDto.Insert data);

    public int deleteIncentiveCheckListById(String id);

    public CiamsFile incentiveCheckListPrint(String id) throws Exception;

    public void uploadCheckList(CiamsIncentiveDetailsDto.IncentiveFile.Insert params) throws CustomException, IOException;

    /**
     *
     */
    public List<CiamsIncentiveDetailsDto.AreaSubject.Find.Result> selectIncentiveLoc(CiamsIncentiveDetailsDto.AreaSubject.Find.Params params);

    public List<CiamsIncentiveDetailsDto.IncentiveInfo.Find.Result> selectIncentiveCalc(CiamsIncentiveDetailsDto.IncentiveInfo.Find.Params params);

    public IncentiveCalculateDto.Details selectIncentiveCalculateDetails(String incentiveId);

    public CiamsIncentiveDetailsDto.IncentiveInfo.Find.Result selectIncentiveCalculateRecord(String calcId);
    public IncentiveCalculateDto.IncentiveInfo selectIncentiveCalculateInfo(IncentiveCalculateDto.Find.Params.Info info);

    public String insertIncentiveCalculate(IncentiveCalculateDto.Insert data);

    public String updateIncentiveCalculate(IncentiveCalculateDto.Insert data);

    public int updateIncentiveStateExamine(String calcId);

    public int updateIncentiveStateFinaliz(String calcId);

    public int deleteIncentiveCalcById(String calcId);


    public List<String> selectGroupByNo();

    public CiamsFile incentivePrint(String calcId) throws Exception;

    /**
     * 모니터링 차트데이터(인센티브현황)
     * @param pnu
     * @return
     */
    public List<CiamsIncentiveDetailsDto.chartGroupData> getChartGroupJimok(List<String> pnu);
    public List<CiamsIncentiveDetailsDto.chartGroupData> getChartGroupAreaUse(List<String> pnu);
    public List<CiamsIncentiveDetailsDto.chartGroupData> getChartGroupYear(List<String> pnu);
    public List<CiamsIncentiveDetailsDto.chartGeometryData> selectGeometrys(List<String> pnu);

    /**
     *
     */
    public List<CiamsIncentiveDto.PNUIncentive> selectPNUIncentive(String pnu);
}
