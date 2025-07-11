package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.CiamsZoneDTO;
import com.uitgis.ciams.dto.PaginationDto;
import com.uitgis.ciams.mapper.CiamsZoneMapper;
import com.uitgis.ciams.service.CiamsZoneService;
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

	@Override
	public CiamsZoneDTO.Search.Result getCiamsZoneList(CiamsZoneDTO.Search.Params params) {
		int totalCount = ciamsZoneMapper.selectCiamsZoneCount(params);

		PaginationDto page = PageUtil.setTotalCount(params, totalCount);

		List<CiamsZoneDTO.Search.Row> rows = ciamsZoneMapper.selectCiamsZoneList(params);

		CiamsZoneDTO.Search.Result result = CiamsZoneDTO.Search.Result.builder()
				.page(page)
				.list(rows)
				.build();

		return result;
	}

	@Override
	public CiamsZoneDTO.Overview.Find.Result getCiamsZoneOverView(CiamsZoneDTO.Overview.Find.Params params) {
		return ciamsZoneMapper.selectCiamsZoneOverView(params);
	}

}
