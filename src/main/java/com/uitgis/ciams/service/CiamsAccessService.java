package com.uitgis.ciams.service;

import java.util.List;

import com.uitgis.ciams.user.dto.CiamsAccessDto;
import com.uitgis.ciams.user.dto.CiamsAccessDto.MenuAccess;

public interface CiamsAccessService {

	List<CiamsAccessDto.RoleResult> getRoleByMenuList(CiamsAccessDto params);

    List<CiamsAccessDto.UserResult> getRoleByUserList(CiamsAccessDto params);

    void addRoleByMenuCode(CiamsAccessDto.Add params) throws Exception;

    void addRoleByUserCode(CiamsAccessDto.Add params) throws Exception;

    void removeRoleByMenuCode(CiamsAccessDto.Delete params) throws Exception;

    void removeRoleByUserCode(CiamsAccessDto.Delete params) throws Exception;

	List<MenuAccess> getMenuAccessList() throws Exception;

}
