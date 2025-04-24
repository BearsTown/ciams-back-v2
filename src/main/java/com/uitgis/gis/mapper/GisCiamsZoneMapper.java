package com.uitgis.gis.mapper;

import com.uitgis.gis.dto.GisCiamsZoneDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GisCiamsZoneMapper {
	public int selectGisCiamsZoneCount(GisCiamsZoneDTO.Search.Params params);

	public List<GisCiamsZoneDTO.Search.Row> selectGisCiamsZoneList(GisCiamsZoneDTO.Search.Params params);
}
