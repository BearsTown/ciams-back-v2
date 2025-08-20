package com.uitgis.ciams.user.service;

import com.uitgis.ciams.user.dto.CiamsZoneDto;

public interface CiamsZoneService {
	/**
	 * 대상지 목록
	 *
	 * @param params
	 * @return
	 */
	CiamsZoneDto.Search.Result getCiamsZones(CiamsZoneDto.Search.Params params);


	/**
	 * 대상지 개요
	 *
	 * @param params
	 * @return
	 */
	CiamsZoneDto.Overview.Find.Result getCiamsZoneOverView(CiamsZoneDto.Overview.Find.Params params);
}
