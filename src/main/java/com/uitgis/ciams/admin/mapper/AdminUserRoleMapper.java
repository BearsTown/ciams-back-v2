package com.uitgis.ciams.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.uitgis.ciams.admin.dto.UserRoleDto;
import com.uitgis.ciams.admin.dto.UserRoleDto.Approve;
import com.uitgis.ciams.model.CiamsUserRole;

@Mapper
public interface AdminUserRoleMapper {
	CiamsUserRole selectById(String userId);

	void insert(UserRoleDto.Add dto);

	int modifyApproveByIds(Approve approve);

	void deleteByIds(@Param("userList") List<String> userList);

	void updateById(CiamsUserRole modifyRole);
}
