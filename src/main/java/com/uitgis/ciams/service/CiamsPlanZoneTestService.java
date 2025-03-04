package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.CiamsMenu3Sub1DetailsDto;
import com.uitgis.ciams.dto.CiamsPlanZoneDto;

public interface CiamsPlanZoneTestService {
	public CiamsMenu3Sub1DetailsDto.Overview.Find.Result getMenu3Sub1OverView(CiamsMenu3Sub1DetailsDto.Overview.Find.Params params);
}
