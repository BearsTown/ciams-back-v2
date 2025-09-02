package com.uitgis.ciams.admin.service;

import com.uitgis.ciams.user.dto.CiamsLoginLogDto;

import java.util.Map;

public interface AdminLoginLogService {
	Map<String, Object> selectList(CiamsLoginLogDto.Find find);
}
