package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.CiamsMenu2Sub2Dto;
import com.uitgis.ciams.model.CiamsDataGroup;

import java.util.List;

public interface Menu1Service {
    public List<CiamsDataGroup> getDataGroups(int parentId);

    public CiamsMenu2Sub2Dto.DataConfig getDataConfig(int dataGroupId);
}
