package com.uitgis.ciams.admin.service.impl;

import com.uitgis.ciams.admin.service.AdminLoginLogService;
import com.uitgis.ciams.model.CiamsLoginLog;
import com.uitgis.ciams.user.dto.CiamsLoginLogDto;
import com.uitgis.ciams.user.dto.CiamsLoginLogDto.Find;
import com.uitgis.ciams.user.dto.PaginationDto;
import com.uitgis.ciams.admin.mapper.AdminLoginLogMapper;
import com.uitgis.ciams.util.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminLoginLogServiceImpl implements AdminLoginLogService {
	private final AdminLoginLogMapper adminLoginLogMapper;


	@Override
	public Map<String, Object> selectList(Find find) {
		int cnt = adminLoginLogMapper.selectCnt(find);

		PaginationDto page = PageUtil.setTotalCount(find, cnt);

		List<CiamsLoginLog> list = adminLoginLogMapper.selectList(find);

		List<CiamsLoginLogDto.GroupFind> group = adminLoginLogMapper.selectGroupList(find);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("list", list);
		resultMap.put("page", page);
		resultMap.put("group", group);

		return resultMap;
	}

}
