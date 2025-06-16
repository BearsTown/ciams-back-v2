package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.Menu2ZoneDetailsDto;
import com.uitgis.ciams.mapper.CiamsDataAttributeMapper;
import com.uitgis.ciams.mapper.CiamsDataColumnMapper;
import com.uitgis.ciams.mapper.CiamsDataGroupMapper;
import com.uitgis.ciams.mapper.CiamsDataMapper;
import com.uitgis.ciams.service.Menu2Service;
import com.uitgis.gis.mapper.GisMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CiamsMenu2ServiceImpl implements Menu2Service {
    private final GisMapper gismapper;
    private final CiamsDataMapper ciamsDataMapper;
    private final CiamsDataColumnMapper ciamsDataColumnMapper;
    private final CiamsDataGroupMapper ciamsDataGroupMapper;
    private final CiamsDataAttributeMapper ciamsDataAttributeMapper;

    @Override
    public Menu2ZoneDetailsDto.Overview.Find.Result getMenu2Sub1OverView(Menu2ZoneDetailsDto.Overview.Find.Params params) {
        return gismapper.selectMenu2Sub1OverView(params);
    }
}
