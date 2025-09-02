package com.uitgis.ciams.service;

import com.uitgis.ciams.user.dto.CiamsUserDto;
import com.uitgis.ciams.user.dto.CiamsUserDto.Modify;

public interface CiamsUserService {
	CiamsUserDto.Select selectById(String loginId);

	void addUser(CiamsUserDto.Add param) throws Exception;

	void modify(Modify param);
}
