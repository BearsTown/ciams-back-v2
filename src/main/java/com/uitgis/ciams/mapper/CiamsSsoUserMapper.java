package com.uitgis.ciams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.uitgis.ciams.dto.CiamsSsoUserDto;
import com.uitgis.ciams.dto.CiamsSsoUserDto.Add;
import com.uitgis.ciams.dto.CiamsSsoUserDto.Lock;
import com.uitgis.ciams.dto.CiamsSsoUserDto.Modify;
import com.uitgis.ciams.model.CiamsSsoUser;

@Mapper
public interface CiamsSsoUserMapper {

	public List<CiamsSsoUserDto.Data> selectList(CiamsSsoUserDto.Find find);

	int selectCnt(CiamsSsoUserDto.Find find);

	CiamsSsoUserDto.Data selectById(String loginId);

	public void insertUser(Add dto);

	int updateById(CiamsSsoUser user);

	int updateLoginFailCnt(String loginId);

	int initLoginFailCnt(String loginId);

	public void deleteByIds(@Param("userList") List<String> userList);

	public void updateLockUsers(Lock lockUsers);

	public void modify(Modify param);
}
