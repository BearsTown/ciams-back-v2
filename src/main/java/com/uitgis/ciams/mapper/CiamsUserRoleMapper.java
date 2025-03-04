package com.uitgis.ciams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.uitgis.ciams.dto.CiamsUserRoleDto;
import com.uitgis.ciams.dto.CiamsUserRoleDto.Approve;
import com.uitgis.ciams.model.CiamsUserRole;

@Mapper
public interface CiamsUserRoleMapper {

	CiamsUserRole selectById(String userId);

	void insert(CiamsUserRoleDto.Add dto);

	int modifyApproveByIds(Approve approve);

	public void deleteByIds(@Param("userList") List<String> userList);

	void updateById(CiamsUserRole modifyRole);
}
