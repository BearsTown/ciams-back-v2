package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.CiamsMenu2Sub1DetailsDto;
import com.uitgis.ciams.dto.CiamsMenu2Sub2Dto;
import com.uitgis.ciams.model.CiamsDataAttribute;
import com.uitgis.ciams.model.CiamsDataGroup;

import java.util.List;

public interface CiamsMenu2Sub1Service {
    public CiamsMenu2Sub1DetailsDto.Overview.Find.Result getMenu2Sub1OverView(CiamsMenu2Sub1DetailsDto.Overview.Find.Params params);

    public List<CiamsDataGroup> getDataGroups(int parentId);

    public CiamsMenu2Sub2Dto.DataConfig getDataConfig(int dataGroupId);
}
