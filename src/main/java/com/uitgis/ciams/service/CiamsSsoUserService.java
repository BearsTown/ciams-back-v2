package com.uitgis.ciams.service;

import com.uitgis.ciams.user.dto.CiamsSsoUserDto;
import com.uitgis.ciams.user.dto.CiamsSsoUserDto.Lock;
import com.uitgis.ciams.user.dto.CiamsSsoUserDto.Modify;

import java.util.List;
import java.util.Map;

public interface CiamsSsoUserService {

	Map<String, Object> selectList(CiamsSsoUserDto.Find find);

	CiamsSsoUserDto.Select selectById(String loginId);

	void addUser(CiamsSsoUserDto.Add param) throws Exception;

	void initPass(String loginId);

	void removeByIds(List<String> userList);

	void updateLockUsers(Lock lockUsers);

	void modify(Modify param);
}
