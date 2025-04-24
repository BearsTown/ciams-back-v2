package com.uitgis.gis.mapper;

import com.uitgis.gis.dto.GisCiamsDistDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GisCiamsDistMapper {
	public int selectGisCiamsDistCount(GisCiamsDistDTO.Search.Params params);

	public List<GisCiamsDistDTO.Search.Row> selectGisCiamsDistList(GisCiamsDistDTO.Search.Params params);
}
