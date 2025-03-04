package com.uitgis.ciams.service;

import java.util.List;
import java.util.Map;

import com.uitgis.ciams.dto.CiamsSsoUserDto;
import com.uitgis.ciams.dto.CiamsSsoUserDto.Lock;
import com.uitgis.ciams.dto.CiamsSsoUserDto.Modify;

public interface CiamsSsoUserService {

	public Map<String, Object> selectList(CiamsSsoUserDto.Find find);

	public CiamsSsoUserDto.Select selectById(String loginId);

	void addUser(CiamsSsoUserDto.Add param) throws Exception;

	public void initPass(String loginId);

	public void removeByIds(List<String> userList);

	public void updateLockUsers(Lock lockUsers);

	public void modify(Modify param);
}
