package com.uitgis.ciams.admin.mapper;

import com.uitgis.ciams.admin.dto.UserDto;
import com.uitgis.ciams.model.CiamsUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminUserMapper {
    List<UserDto.Data> selectList(UserDto.Find find);

    int selectCnt(UserDto.Find find);

    UserDto.Data selectById(String loginId);

    int updateById(CiamsUser user);

    int updateLoginFailCnt(String loginId);

    int initLoginFailCnt(String loginId);

    void deleteByIds(@Param("userList") List<String> userList);

    void updateLockUsers(UserDto.Lock lockUsers);
}
