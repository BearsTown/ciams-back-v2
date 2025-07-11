package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.CiamsZoneDTO;

public interface CiamsZoneService {
	public CiamsZoneDTO.Search.Result getCiamsZoneList(CiamsZoneDTO.Search.Params params);

	public CiamsZoneDTO.Overview.Find.Result getCiamsZoneOverView(CiamsZoneDTO.Overview.Find.Params params);
}
