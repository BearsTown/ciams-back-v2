package com.uitgis.ciams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.uitgis.ciams.dto.CiamsBdRegDto;

@Mapper
public interface CiamsBdRegMapper {

	public List<CiamsBdRegDto.Search.Row> selectBdReg(CiamsBdRegDto.Search.param params);

}
