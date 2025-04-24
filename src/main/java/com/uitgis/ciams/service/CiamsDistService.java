package com.uitgis.ciams.service;

import com.uitgis.gis.dto.GisCiamsDistDTO;

public interface CiamsDistService {
	public GisCiamsDistDTO.Search.Result getCiamsDistList(GisCiamsDistDTO.Search.Params params);
}
