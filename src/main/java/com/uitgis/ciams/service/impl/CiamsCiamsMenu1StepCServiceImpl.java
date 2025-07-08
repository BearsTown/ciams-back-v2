package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.CiamsMenu1StepCDetailsDto;
import com.uitgis.ciams.service.CiamsMenu1StepCService;
import com.uitgis.gis.mapper.GisMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CiamsCiamsMenu1StepCServiceImpl implements CiamsMenu1StepCService {
    private final GisMapper gismapper;


    @Override
    public CiamsMenu1StepCDetailsDto.Overview.Find.Result getOverView(CiamsMenu1StepCDetailsDto.Overview.Find.Params params) {
        return gismapper.selectOverView(params);
    }
}
