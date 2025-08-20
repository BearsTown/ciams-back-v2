package com.uitgis.ciams.user.service.impl;

import com.uitgis.ciams.user.dto.CiamsZoneDto;
import com.uitgis.ciams.user.dto.PaginationDto;
import com.uitgis.ciams.user.mapper.CiamsZoneMapper;
import com.uitgis.ciams.user.service.CiamsZoneService;
import com.uitgis.ciams.util.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsZoneServiceImpl implements CiamsZoneService {
	private final CiamsZoneMapper ciamsZoneMapper;


	/**
	 * 대상지 목록
	 *
	 * @param params
	 * @return
	 */
	@Override
	public CiamsZoneDto.Search.Result getCiamsZones(CiamsZoneDto.Search.Params params) {
		int totalCount = ciamsZoneMapper.countCiamsZone(params);

		PaginationDto page = PageUtil.setTotalCount(params, totalCount);

		List<CiamsZoneDto.Search.Row> rows = ciamsZoneMapper.findAllCiamsZones(params);

		CiamsZoneDto.Search.Result result = CiamsZoneDto.Search.Result.builder()
				.page(page)
				.list(rows)
				.build();

		return result;
	}


	/**
	 * 대상지 개요
	 *
	 * @param params
	 * @return
	 */
	@Override
	public CiamsZoneDto.Overview.Find.Result getCiamsZoneOverView(CiamsZoneDto.Overview.Find.Params params) {
		return ciamsZoneMapper.findCiamsZoneOverView(params);
	}

}
