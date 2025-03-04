package com.uitgis.ciams.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uitgis.ciams.dto.CiamsBdRegDto;
import com.uitgis.ciams.dto.CiamsBdRegDto.Search.param;
import com.uitgis.ciams.mapper.CiamsBdRegMapper;
import com.uitgis.ciams.service.CiamsBdRegService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsBdRegServiceImpl implements CiamsBdRegService {
	private final CiamsBdRegMapper ciamsbdregmapper;

	@Override
	public List<CiamsBdRegDto.Search.Row> selectBdReg(param param) {
		return ciamsbdregmapper.selectBdReg(param);
	}

}
