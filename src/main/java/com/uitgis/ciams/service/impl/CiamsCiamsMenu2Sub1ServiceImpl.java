package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.CiamsMenu2Sub1DetailsDto;
import com.uitgis.ciams.service.CiamsMenu2Sub1Service;
import com.uitgis.gis.mapper.GisMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CiamsCiamsMenu2Sub1ServiceImpl implements CiamsMenu2Sub1Service {
    private final GisMapper gismapper;

    @Override
    public CiamsMenu2Sub1DetailsDto.Overview.Find.Result getMenu2Sub1OverView(CiamsMenu2Sub1DetailsDto.Overview.Find.Params params) {
        return gismapper.selectMenu2Sub1OverView(params);
    }
}
