package com.uitgis.ciams.admin.service;

import java.util.Map;

import com.uitgis.ciams.user.dto.CiamsLoginLogDto;

public interface CiamsLoginLogService {

	public Map<String, Object> selectList(CiamsLoginLogDto.Find find);
}
