package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.Menu3Sub1DetailsDto;
import com.uitgis.ciams.mapper.Menu3Sub1Mapper;
import com.uitgis.ciams.model.CiamsImage;
import com.uitgis.ciams.service.Menu3Sub1Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Menu3Sub1ServiceImpl implements Menu3Sub1Service {
    private final Menu3Sub1Mapper menu3Sub1Mapper;

    @Override
    public Menu3Sub1DetailsDto.Info.Find.Result getMenu3Sub1Info(Menu3Sub1DetailsDto.Info.Find.Params params) {
        return menu3Sub1Mapper.selectMenu3Sub1Info(params);
    }

    @Override
    public CiamsImage getImage(int id) {
        return menu3Sub1Mapper.selectImage(id);
    }
}
