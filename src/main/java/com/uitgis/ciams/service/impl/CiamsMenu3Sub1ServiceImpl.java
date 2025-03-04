package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.CiamsMenu3Sub1DetailsDto;
import com.uitgis.ciams.mapper.CiamsPlanZoneMapper;
import com.uitgis.ciams.model.CiamsImage;
import com.uitgis.ciams.service.CiamsMenu3Sub1Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CiamsMenu3Sub1ServiceImpl implements CiamsMenu3Sub1Service {
    private final CiamsPlanZoneMapper ciamsPlanZoneMapper;

    @Override
    public CiamsMenu3Sub1DetailsDto.Overview.Find.Result getMenu3Sub1OverView(CiamsMenu3Sub1DetailsDto.Overview.Find.Params params) {
        return ciamsPlanZoneMapper.selectMenu3Sub1OverView(params);
    }

    @Override
    public CiamsImage getImage(int id) {
        return ciamsPlanZoneMapper.selectImage(id);
    }
}
