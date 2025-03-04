package com.uitgis.gis.mapper;

import com.uitgis.gis.dto.GisCiamsPlanZoneDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GisCiamsPlanZoneMapper {
	public int selectGisCiamsPlanZoneCount(GisCiamsPlanZoneDTO.Search.Params params);

	public List<GisCiamsPlanZoneDTO.Search.Row> selectGisCiamsPlanZoneList(GisCiamsPlanZoneDTO.Search.Params params);
}
