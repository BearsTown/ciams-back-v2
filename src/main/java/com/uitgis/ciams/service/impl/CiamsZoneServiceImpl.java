package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.PaginationDto;
import com.uitgis.ciams.service.CiamsZoneService;
import com.uitgis.ciams.util.PageUtil;
import com.uitgis.gis.dto.GisCiamsZoneDTO;
import com.uitgis.gis.mapper.GisCiamsZoneMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsZoneServiceImpl implements CiamsZoneService {
	private final GisCiamsZoneMapper gisCiamsZoneMapper;

	@Override
	public GisCiamsZoneDTO.Search.Result getCiamsZoneList(GisCiamsZoneDTO.Search.Params params) {
		int totalCount = gisCiamsZoneMapper.selectGisCiamsZoneCount(params);

		PaginationDto page = PageUtil.setTotalCount(params, totalCount);

		List<GisCiamsZoneDTO.Search.Row> rows = gisCiamsZoneMapper.selectGisCiamsZoneList(params);

		GisCiamsZoneDTO.Search.Result result = GisCiamsZoneDTO.Search.Result.builder()
				.page(page)
				.list(rows)
				.build();

		return result;
	}

}
