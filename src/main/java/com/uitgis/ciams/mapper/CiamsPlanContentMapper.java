package com.uitgis.ciams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.uitgis.ciams.dto.CiamsPlanContentDto;
import com.uitgis.ciams.dto.CiamsPlanContentDto.Search;
import com.uitgis.ciams.model.CiamsPlanContent;

@Mapper
public interface CiamsPlanContentMapper {

	List<CiamsPlanContentDto.SelectDetail> selectList(Search params);

	List<CiamsPlanContentDto.SelectDetail> selectAreaList(Search params);

	CiamsPlanContent select(String planContentId);

	int insert(CiamsPlanContent model);

	int update(CiamsPlanContent model);

	int deleteById(String planContentId);

}
