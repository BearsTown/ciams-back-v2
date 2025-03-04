package com.uitgis.ciams.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uitgis.ciams.dto.CiamsBdEtcDto;
import com.uitgis.ciams.dto.CiamsBdEtcDto.Search.param;
import com.uitgis.gis.mapper.CiamsBdEtcMapper;
import com.uitgis.ciams.service.CiamsBdEtcService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsBdEtcServiceImpl implements CiamsBdEtcService {
	private final CiamsBdEtcMapper ciamsbdetcmapper;

	@Override
	public List<CiamsBdEtcDto.Search.Row> selectBdEtc(param param) {
		return ciamsbdetcmapper.selectBdEtc(param);
	}
}
