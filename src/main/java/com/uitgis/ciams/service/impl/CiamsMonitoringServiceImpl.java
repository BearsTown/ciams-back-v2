package com.uitgis.ciams.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uitgis.gis.mapper.GisMapper;
import com.uitgis.ciams.dto.CiamsMonitoringDto.BuildingOldResult;
import com.uitgis.ciams.dto.CiamsMonitoringDto.BuildingTypeResult;
import com.uitgis.ciams.dto.CiamsMonitoringDto.CommonResult;
import com.uitgis.ciams.dto.CiamsMonitoringDto.Search.District;
import com.uitgis.ciams.dto.CiamsMonitoringDto.Search.Parcel;
import com.uitgis.ciams.dto.CiamsMonitoringDto.Search.PlanArea;
import com.uitgis.ciams.service.CiamsMonitoringService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsMonitoringServiceImpl implements CiamsMonitoringService {
	private final GisMapper gismapper;

	@Override
	public List<BuildingTypeResult> getBuildingByDistrict(District params) {
		return gismapper.getBuildingByDistrict(params);
	}

	@Override
	public List<BuildingTypeResult> getBuildingBySgg(District params) {
		return gismapper.getBuildingBySgg(params);
	}

	@Override
	public List<BuildingTypeResult> getBuildingByPlanarea(PlanArea params) {
		return gismapper.getBuildingByPlanarea(params);
	}

	@Override
	public List<BuildingOldResult> getBuildingOldByDistrict(District params) {
		return gismapper.getBuildingOldByDistrict(params);
	}

	@Override
	public List<BuildingOldResult> getBuildingOldByPlanarea(PlanArea params) {
		return gismapper.getBuildingOldByPlanarea(params);
	}

	@Override
	public List<CommonResult> getParcelTypeByDistrict(Parcel params) {
		return gismapper.getParcelTypeByDistrict(params);
	}

	@Override
	public List<CommonResult> getParcelOwnerByDistrict(Parcel params) {
		return gismapper.getParcelOwnerByDistrict(params);
	}

}
