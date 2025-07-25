package com.uitgis.gis.mapper;

import com.uitgis.gis.dto.GisCiamsDistDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GisCiamsDistMapper {
	int countGisCiamsDist(GisCiamsDistDTO.Search.Params params);

	List<GisCiamsDistDTO.Search.Row> findAllGisCiamsDists(GisCiamsDistDTO.Search.Params params);
}
