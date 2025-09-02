package com.uitgis.ciams.admin.service.impl;

import com.uitgis.ciams.admin.dto.UserRoleDto;
import com.uitgis.ciams.admin.service.AdminUserRoleService;
import com.uitgis.ciams.admin.mapper.AdminUserRoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminUserRoleServiceImpl implements AdminUserRoleService {

	private final AdminUserRoleMapper adminUserRoleMapper;


	@Override
	public int modifyApprove(UserRoleDto.Approve approve) {
		return adminUserRoleMapper.modifyApproveByIds(approve);
	}
}
