package com.uitgis.ciams.admin.service.impl;

import org.springframework.stereotype.Service;

import com.uitgis.ciams.user.dto.CiamsUserRoleDto;
import com.uitgis.ciams.user.mapper.CiamsUserRoleMapper;
import com.uitgis.ciams.admin.service.CiamsUserRoleService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CiamsUserRoleServiceImpl implements CiamsUserRoleService {

	private final CiamsUserRoleMapper ciamsUserRoleMapper;

	@Override
	public int modifyApprove(CiamsUserRoleDto.Approve approve) {
		return ciamsUserRoleMapper.modifyApproveByIds(approve);
	}
}
