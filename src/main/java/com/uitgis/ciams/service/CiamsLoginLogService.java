package com.uitgis.ciams.service;

import java.util.Map;

import com.uitgis.ciams.dto.CiamsLoginLogDto;

public interface CiamsLoginLogService {

	public Map<String, Object> selectList(CiamsLoginLogDto.Find find);
}
