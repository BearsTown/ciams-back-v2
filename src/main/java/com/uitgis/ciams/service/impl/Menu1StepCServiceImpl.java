package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.Menu1StepCDetailsDto;
import com.uitgis.ciams.service.Menu1StepCService;
import com.uitgis.gis.mapper.GisMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Menu1StepCServiceImpl implements Menu1StepCService {
    private final GisMapper gismapper;


    @Override
    public Menu1StepCDetailsDto.Overview.Find.Result getOverView(Menu1StepCDetailsDto.Overview.Find.Params params) {
        return gismapper.selectOverView(params);
    }
}
