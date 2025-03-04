package com.uitgis.ciams.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uitgis.ciams.dto.CiamsAccessDto;
import com.uitgis.ciams.dto.CiamsAccessDto.Add;
import com.uitgis.ciams.dto.CiamsAccessDto.UserResult;
import com.uitgis.ciams.mapper.CiamsAccessMapper;
import com.uitgis.ciams.model.CiamsAccessRoleLink;
import com.uitgis.ciams.model.CiamsAccessUserLink;
import com.uitgis.ciams.service.CiamsAccessService;
import com.uitgis.ciams.service.CiamsCommonService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CiamsAccessServiceImpl implements CiamsAccessService{

	private final CiamsAccessMapper ciamsAccessMapper;
	private final CiamsCommonService ciamsCommonService;

	@Override
	public List<CiamsAccessDto.RoleResult> getRoleByMenuList(CiamsAccessDto params) {
		return ciamsAccessMapper.selectRoleByMenuList(params);
	}

	@Override
	public List<UserResult> getRoleByUserList(CiamsAccessDto params) {
		return ciamsAccessMapper.selectRoleByUserList(params);
	}

	@Override
	public void addRoleByMenuCode(Add params) throws Exception {
		params.getMenus().forEach(menuCode->{

			CiamsAccessRoleLink dto = new CiamsAccessRoleLink();
			dto.setAccessMenuCode(menuCode);
			dto.setAccessRoleCode(params.getRoleCode());
			dto.setRegUser(ciamsCommonService.getUsername());

			ciamsAccessMapper.insertRole(dto);
		});
	}

	@Override
	public void addRoleByUserCode(Add params) throws Exception {
		params.getUsers().forEach(userId->{

			CiamsAccessUserLink dto = new CiamsAccessUserLink();
			dto.setAccessRoleCode(params.getRoleCode());
			dto.setUserId(userId);
			dto.setRegUser(ciamsCommonService.getUsername());

			ciamsAccessMapper.insertUser(dto);
		});
	}

	@Override
	public void removeRoleByMenuCode(CiamsAccessDto.Delete params) throws Exception {
		ciamsAccessMapper.deleteRoles(params);
	}

	@Override
	public void removeRoleByUserCode(CiamsAccessDto.Delete params) throws Exception {
		ciamsAccessMapper.deleteUsers(params);
	}

	@Override
	public List<CiamsAccessDto.MenuAccess> getMenuAccessList() throws Exception {
		String loginId = ciamsCommonService.getUsername();
		return ciamsAccessMapper.selectMenuAccess(loginId);
	}



}
