package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.Menu2Sub1DetailsDto;
import com.uitgis.ciams.mapper.Menu2Sub1Mapper;
import com.uitgis.ciams.service.Menu2Sub1Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class Menu2Sub1ServiceImpl implements Menu2Sub1Service {
	private final Menu2Sub1Mapper menu2Sub1Mapper;

	@Override
	public List<Menu2Sub1DetailsDto.ItaResultData> getByZoneNoItaResultDatas(String zoneNo) {
		return menu2Sub1Mapper.selectByZoneNoItaResultDatas(zoneNo);
	}
}
