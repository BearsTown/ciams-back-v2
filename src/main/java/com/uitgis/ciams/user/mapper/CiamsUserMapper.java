package com.uitgis.ciams.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.uitgis.ciams.user.dto.CiamsUserDto;
import com.uitgis.ciams.user.dto.CiamsUserDto.Add;
import com.uitgis.ciams.model.CiamsUser;

@Mapper
public interface CiamsUserMapper {
	List<CiamsUserDto.Data> selectList(CiamsUserDto.Find find);

	CiamsUserDto.Data selectById(String loginId);

	void insertUser(Add dto);

	int updateById(CiamsUser user);
}
