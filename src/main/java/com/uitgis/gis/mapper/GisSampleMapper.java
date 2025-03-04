package com.uitgis.gis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.uitgis.gis.model.GisSampleModel;

@Mapper
public interface GisSampleMapper {
	List<GisSampleModel> select();
}
