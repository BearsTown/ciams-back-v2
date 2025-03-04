package com.uitgis.ciams.service;

import java.util.List;

import com.uitgis.ciams.dto.CiamsMonitoringDto;
import com.uitgis.ciams.dto.CiamsMonitoringDto.BuildingTypeResult;
import com.uitgis.ciams.dto.CiamsMonitoringDto.CommonResult;
import com.uitgis.ciams.dto.CiamsMonitoringDto.Search.Parcel;

public interface CiamsMonitoringService {

	public List<CiamsMonitoringDto.BuildingTypeResult> getBuildingByDistrict(CiamsMonitoringDto.Search.District params);

	public List<CiamsMonitoringDto.BuildingTypeResult> getBuildingBySgg(CiamsMonitoringDto.Search.District params);

	public List<CiamsMonitoringDto.BuildingTypeResult> getBuildingByPlanarea(CiamsMonitoringDto.Search.PlanArea params);
	
	public List<CiamsMonitoringDto.BuildingOldResult> getBuildingOldByDistrict(CiamsMonitoringDto.Search.District params);

	public List<CiamsMonitoringDto.BuildingOldResult> getBuildingOldByPlanarea(CiamsMonitoringDto.Search.PlanArea params);

	public List<CommonResult> getParcelTypeByDistrict(Parcel params);

	public List<CommonResult> getParcelOwnerByDistrict(Parcel params);
}
