package com.uitgis.ciams.mapper;

import com.uitgis.ciams.dto.CiamsZoneDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsZoneMapper {
    public int selectCiamsZoneCount(CiamsZoneDTO.Search.Params params);

    public List<CiamsZoneDTO.Search.Row> selectCiamsZoneList(CiamsZoneDTO.Search.Params params);

    public CiamsZoneDTO.Overview.Find.Result selectCiamsZoneOverView(CiamsZoneDTO.Overview.Find.Params params);
}
