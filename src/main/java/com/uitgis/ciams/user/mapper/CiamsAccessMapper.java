package com.uitgis.ciams.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.uitgis.ciams.user.dto.CiamsAccessDto;
import com.uitgis.ciams.model.CiamsAccessRoleLink;
import com.uitgis.ciams.model.CiamsAccessUserLink;

@Mapper
public interface CiamsAccessMapper {

	List<CiamsAccessDto.RoleResult> selectRoleByMenuList(CiamsAccessDto params);
	List<CiamsAccessDto.UserResult> selectRoleByUserList(CiamsAccessDto params);

	int insertUser(CiamsAccessUserLink params);
	int insertRole(CiamsAccessRoleLink params);

	int deleteUsers(CiamsAccessDto.Delete params);
	int deleteRoles(CiamsAccessDto.Delete params);

	List<CiamsAccessDto.MenuAccess> selectMenuAccess(String loginId);
}
