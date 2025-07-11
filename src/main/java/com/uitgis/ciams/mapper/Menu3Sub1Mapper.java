package com.uitgis.ciams.mapper;

import com.uitgis.ciams.dto.Menu3Sub1DetailsDto;
import com.uitgis.ciams.model.CiamsImage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Menu3Sub1Mapper {
    public Menu3Sub1DetailsDto.Info.Find.Result selectMenu3Sub1Info(Menu3Sub1DetailsDto.Info.Find.Params params);

    public CiamsImage selectImage(int id);
}
