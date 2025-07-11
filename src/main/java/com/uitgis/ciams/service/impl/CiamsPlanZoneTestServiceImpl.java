package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.Menu3Sub1DetailsDto;
import com.uitgis.ciams.mapper.Menu3Sub1Mapper;
import com.uitgis.ciams.service.CiamsPlanZoneTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsPlanZoneTestServiceImpl implements CiamsPlanZoneTestService {
	private final Menu3Sub1Mapper menu3Sub1Mapper;


	@Override
	public Menu3Sub1DetailsDto.Info.Find.Result getMenu3Sub1OverView(Menu3Sub1DetailsDto.Info.Find.Params params) {
		return menu3Sub1Mapper.selectMenu3Sub1Info(params);
	}
}
