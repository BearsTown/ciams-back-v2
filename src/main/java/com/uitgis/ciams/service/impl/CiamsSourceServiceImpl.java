package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.CiamsSourceGroupDto;
import com.uitgis.ciams.dto.Menu2Sub1DetailsDto;
import com.uitgis.ciams.mapper.CiamsSourceMapper;
import com.uitgis.ciams.mapper.Menu2Sub1Mapper;
import com.uitgis.ciams.service.CiamsSourceService;
import com.uitgis.ciams.service.Menu2Sub1Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsSourceServiceImpl implements CiamsSourceService {
	private final CiamsSourceMapper ciamsSourceMapper;

	@Override
	public List<CiamsSourceGroupDto.Find.Result> getSources(CiamsSourceGroupDto.Find.Params params) {
		return ciamsSourceMapper.selectSources(params);
	}
}
