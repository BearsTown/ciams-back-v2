package com.uitgis.ciams.service.impl;

import org.springframework.stereotype.Service;

import com.uitgis.ciams.dto.CiamsUserRoleDto;
import com.uitgis.ciams.mapper.CiamsUserRoleMapper;
import com.uitgis.ciams.service.CiamsUserRoleSerice;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CiamsUserRoleServiceImpl implements CiamsUserRoleSerice {

	private final CiamsUserRoleMapper ciamsUserRoleMapper;

	@Override
	public int modifyApprove(CiamsUserRoleDto.Approve approve) {
		return ciamsUserRoleMapper.modifyApproveByIds(approve);
	}
}
