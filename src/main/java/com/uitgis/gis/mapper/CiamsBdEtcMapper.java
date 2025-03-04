package com.uitgis.gis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.uitgis.ciams.dto.CiamsBdEtcDto;

@Mapper
public interface CiamsBdEtcMapper {

	public List<CiamsBdEtcDto.Search.Row> selectBdEtc(CiamsBdEtcDto.Search.param params);

}
