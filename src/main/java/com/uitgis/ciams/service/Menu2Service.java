package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.CiamsMenu2Sub2Dto;
import com.uitgis.ciams.dto.Menu2ZoneDetailsDto;
import com.uitgis.ciams.model.CiamsDataGroup;

import java.util.List;

public interface Menu2Service {
    public Menu2ZoneDetailsDto.Overview.Find.Result getMenu2Sub1OverView(Menu2ZoneDetailsDto.Overview.Find.Params params);
}
