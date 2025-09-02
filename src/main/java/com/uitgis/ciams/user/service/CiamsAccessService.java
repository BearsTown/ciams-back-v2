package com.uitgis.ciams.user.service;

import com.uitgis.ciams.user.dto.CiamsAccessDto.MenuAccess;

import java.util.List;

public interface CiamsAccessService {
    List<MenuAccess> getMenuAccessList() throws Exception;
}
