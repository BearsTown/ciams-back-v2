package com.uitgis.ciams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.uitgis.ciams.model.CiamsPlanAreaLink;

@Mapper
public interface CiamsPlanAreaLinkMapper {

	public List<CiamsPlanAreaLink> selectByAreaId(String planAreaId);

	public int update(CiamsPlanAreaLink param);
}
