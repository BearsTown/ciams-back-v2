package com.uitgis.ciams.admin.service.impl;

import com.uitgis.ciams.admin.dto.AccessDto;
import com.uitgis.ciams.admin.mapper.AdminAccessMapper;
import com.uitgis.ciams.admin.service.AdminAccessService;
import com.uitgis.ciams.model.CiamsAccessRoleLink;
import com.uitgis.ciams.model.CiamsAccessUserLink;
import com.uitgis.ciams.service.CiamsCommonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminAccessServiceImpl implements AdminAccessService {

	private final AdminAccessMapper adminAccessMapper;
	private final CiamsCommonService ciamsCommonService;


	@Override
	public List<AccessDto.RoleResult> getRoleByMenuList(AccessDto params) {
		return adminAccessMapper.selectRoleByMenuList(params);
	}


	@Override
	public List<AccessDto.UserResult> getRoleByUserList(AccessDto params) {
		return adminAccessMapper.selectRoleByUserList(params);
	}


	@Override
	public void addRoleByMenuCode(AccessDto.Add params) throws Exception {
		params.getMenus().forEach(menuCode->{

			CiamsAccessRoleLink dto = new CiamsAccessRoleLink();
			dto.setAccessMenuCode(menuCode);
			dto.setAccessRoleCode(params.getRoleCode());
			dto.setRegUser(ciamsCommonService.getUsername());

			adminAccessMapper.insertRole(dto);
		});
	}


	@Override
	public void addRoleByUserCode(AccessDto.Add params) throws Exception {
		params.getUsers().forEach(userId->{

			CiamsAccessUserLink dto = new CiamsAccessUserLink();
			dto.setAccessRoleCode(params.getRoleCode());
			dto.setUserId(userId);
			dto.setRegUser(ciamsCommonService.getUsername());

			adminAccessMapper.insertUser(dto);
		});
	}


	@Override
	public void removeRoleByMenuCode(AccessDto.Delete params) throws Exception {
		adminAccessMapper.deleteRoles(params);
	}


	@Override
	public void removeRoleByUserCode(AccessDto.Delete params) throws Exception {
		adminAccessMapper.deleteUsers(params);
	}

}
