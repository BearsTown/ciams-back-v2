package com.uitgis.ciams.service;

import com.uitgis.gis.dto.GisCiamsZoneDTO;

public interface CiamsZoneService {
	public GisCiamsZoneDTO.Search.Result getCiamsZoneList(GisCiamsZoneDTO.Search.Params params);

}
