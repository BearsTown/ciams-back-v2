package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.CiamsSourceGroupDto;
import com.uitgis.ciams.mapper.CiamsSourceMapper;
import com.uitgis.ciams.service.CiamsSourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsSourceServiceImpl implements CiamsSourceService {
	private final CiamsSourceMapper ciamsSourceMapper;


	/**
	 * 출처 목록
	 *
	 * @param params
	 * @return
	 */
	@Override
	public List<CiamsSourceGroupDto.Find.Result> getSources(CiamsSourceGroupDto.Find.Params params) {
		return ciamsSourceMapper.findAllSources(params);
	}

}
