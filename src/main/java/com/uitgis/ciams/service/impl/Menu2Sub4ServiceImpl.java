package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.Menu2Sub4DetailsDto;
import com.uitgis.ciams.mapper.CiamsAnalysisMapper;
import com.uitgis.ciams.model.CiamsImage;
import com.uitgis.ciams.service.Menu2Sub4Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Menu2Sub4ServiceImpl implements Menu2Sub4Service {
    private final CiamsAnalysisMapper ciamsAnalysisMapper;

    @Override
    public Menu2Sub4DetailsDto.Overview.Find.Result getMenu2Sub4OverView(Menu2Sub4DetailsDto.Overview.Find.Params params) {
        return ciamsAnalysisMapper.selectMenu2Sub4OverView(params);
    }

    @Override
    public Menu2Sub4DetailsDto.Info getAnalysisInfo(String zoneNo) {
        return ciamsAnalysisMapper.selectAnalysisInfo(zoneNo);
    }

    @Override
    public CiamsImage getImage(int id) {
        return ciamsAnalysisMapper.selectImage(id);
    }
}
