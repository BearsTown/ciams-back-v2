package com.uitgis.ciams.user.service;

import com.uitgis.gis.dto.GisCiamsDistDTO;

public interface CiamsDistService {
	GisCiamsDistDTO.Search.Result getCiamsDistList(GisCiamsDistDTO.Search.Params params);
}
