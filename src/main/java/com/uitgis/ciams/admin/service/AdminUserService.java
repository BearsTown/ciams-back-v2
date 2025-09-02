package com.uitgis.ciams.admin.service;

import com.uitgis.ciams.admin.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface AdminUserService {
    Map<String, Object> selectList(UserDto.Find find);

    UserDto.Select selectById(String loginId);

    void initPass(String loginId);

    void removeByIds(List<String> userList);

    void updateLockUsers(UserDto.Lock lockUsers);

    void modify(UserDto.Modify param);
}
