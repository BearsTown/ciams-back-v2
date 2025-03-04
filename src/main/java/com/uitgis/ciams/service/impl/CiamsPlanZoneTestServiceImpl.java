package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.CiamsMenu3Sub1DetailsDto;
import com.uitgis.ciams.dto.CiamsPlanZoneDto;
import com.uitgis.ciams.mapper.CiamsPlanZoneMapper;
import com.uitgis.ciams.service.CiamsPlanZoneTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsPlanZoneTestServiceImpl implements CiamsPlanZoneTestService {
	private final CiamsPlanZoneMapper ciamsPlanZoneMapper;


	@Override
	public CiamsMenu3Sub1DetailsDto.Overview.Find.Result getMenu3Sub1OverView(CiamsMenu3Sub1DetailsDto.Overview.Find.Params params) {
		return ciamsPlanZoneMapper.selectMenu3Sub1OverView(params);
	}
}
