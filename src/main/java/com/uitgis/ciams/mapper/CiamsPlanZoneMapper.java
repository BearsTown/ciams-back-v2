package com.uitgis.ciams.mapper;

import com.uitgis.ciams.dto.CiamsMenu3Sub1DetailsDto;
import com.uitgis.ciams.dto.CiamsPlanZoneDto;
import com.uitgis.ciams.model.CiamsImage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CiamsPlanZoneMapper {
    public CiamsMenu3Sub1DetailsDto.Overview.Find.Result selectMenu3Sub1OverView(CiamsMenu3Sub1DetailsDto.Overview.Find.Params params);

    public CiamsImage selectImage(int id);
}
