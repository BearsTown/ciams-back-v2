package com.uitgis.ciams.admin.mapper;

import com.uitgis.ciams.admin.dto.AccessDto;
import com.uitgis.ciams.model.CiamsAccessRoleLink;
import com.uitgis.ciams.model.CiamsAccessUserLink;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminAccessMapper {
    List<AccessDto.RoleResult> selectRoleByMenuList(AccessDto params);

    List<AccessDto.UserResult> selectRoleByUserList(AccessDto params);

    int insertUser(CiamsAccessUserLink params);

    int insertRole(CiamsAccessRoleLink params);

    int deleteUsers(AccessDto.Delete params);

    int deleteByIds(@Param("userList") List<String> userList);

    int deleteRoles(AccessDto.Delete params);

    List<AccessDto.MenuAccess> selectMenuAccess(String loginId);
}
