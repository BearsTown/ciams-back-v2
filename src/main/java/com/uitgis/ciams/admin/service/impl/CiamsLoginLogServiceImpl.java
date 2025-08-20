package com.uitgis.ciams.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.uitgis.ciams.user.dto.CiamsLoginLogDto;
import com.uitgis.ciams.user.dto.CiamsLoginLogDto.Find;
import com.uitgis.ciams.user.dto.PaginationDto;
import com.uitgis.ciams.user.mapper.CiamsLoginLogMapper;
import com.uitgis.ciams.model.CiamsLoginLog;
import com.uitgis.ciams.admin.service.CiamsLoginLogService;
import com.uitgis.ciams.util.PageUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsLoginLogServiceImpl implements CiamsLoginLogService {

	private final CiamsLoginLogMapper ciamsLoginLogMapper;

	@Override
	public Map<String, Object> selectList(Find find) {
		int cnt = ciamsLoginLogMapper.selectCnt(find);

		PaginationDto page = PageUtil.setTotalCount(find, cnt);

		List<CiamsLoginLog> list = ciamsLoginLogMapper.selectList(find);

		List<CiamsLoginLogDto.GroupFind> group = ciamsLoginLogMapper.selectGroupList(find);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("list", list);
		resultMap.put("page", page);
		resultMap.put("group", group);

		return resultMap;
	}

}
