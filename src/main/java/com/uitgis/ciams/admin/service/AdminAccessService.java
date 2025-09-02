package com.uitgis.ciams.admin.service;


import com.uitgis.ciams.admin.dto.AccessDto;

import java.util.List;

public interface AdminAccessService {

    List<AccessDto.RoleResult> getRoleByMenuList(AccessDto params);

    List<AccessDto.UserResult> getRoleByUserList(AccessDto params);

    void addRoleByMenuCode(AccessDto.Add params) throws Exception;

    void addRoleByUserCode(AccessDto.Add params) throws Exception;

    void removeRoleByMenuCode(AccessDto.Delete params) throws Exception;

    void removeRoleByUserCode(AccessDto.Delete params) throws Exception;

}
