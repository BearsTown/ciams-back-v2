package com.uitgis.ciams.mapper;

import com.uitgis.ciams.dto.Menu2Sub4DetailsDto;
import com.uitgis.ciams.model.CiamsImage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CiamsAnalysisMapper {
    public Menu2Sub4DetailsDto.Overview.Find.Result selectMenu2Sub4OverView(Menu2Sub4DetailsDto.Overview.Find.Params params);

    public Menu2Sub4DetailsDto.Info selectAnalysisInfo(String zoneNo);

    public CiamsImage selectImage(int id);
}
