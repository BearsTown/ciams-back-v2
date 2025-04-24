package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.PaginationDto;
import com.uitgis.ciams.service.CiamsDistService;
import com.uitgis.ciams.util.PageUtil;
import com.uitgis.gis.dto.GisCiamsDistDTO;
import com.uitgis.gis.mapper.GisCiamsDistMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsDistServiceImpl implements CiamsDistService {
	private final GisCiamsDistMapper gisCiamsDistMapper;


	@Override
	public GisCiamsDistDTO.Search.Result getCiamsDistList(GisCiamsDistDTO.Search.Params params) {
		int totalCount = gisCiamsDistMapper.selectGisCiamsDistCount(params);

		PaginationDto page = PageUtil.setTotalCount(params, totalCount);

		List<GisCiamsDistDTO.Search.Row> rows = gisCiamsDistMapper.selectGisCiamsDistList(params);

		GisCiamsDistDTO.Search.Result result = GisCiamsDistDTO.Search.Result.builder()
				.page(page)
				.list(rows)
				.build();

		return result;
	}
}
