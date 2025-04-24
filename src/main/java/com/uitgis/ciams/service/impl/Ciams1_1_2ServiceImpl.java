package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.Ciams1_1_2Dto;
import com.uitgis.ciams.mapper.Ciams1_1_2Mapper;
import com.uitgis.ciams.service.Ciams1_1_2Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class Ciams1_1_2ServiceImpl implements Ciams1_1_2Service {
	private final Ciams1_1_2Mapper ciams1_1_2Mapper;

	@Override
	public Ciams1_1_2Dto.DensityInfo getDensityInfos(String type) {
		return ciams1_1_2Mapper.selectDensityInfos(type);
	}
}
