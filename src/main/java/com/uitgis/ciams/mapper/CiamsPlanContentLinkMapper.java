package com.uitgis.ciams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.uitgis.ciams.dto.CiamsPlanContentLinkDto;
import com.uitgis.ciams.dto.CiamsPlanContentLinkDto.Select;
import com.uitgis.ciams.model.CiamsPlanContentLink;

@Mapper
public interface CiamsPlanContentLinkMapper {

	int insert(CiamsPlanContentLink model);
	
	int updateBatch(List<CiamsPlanContentLink> model);

	int insertBatch(List<CiamsPlanContentLink> model);

	int update(CiamsPlanContentLink model);

	int delete(CiamsPlanContentLink model);

	int deleteByContentId(String planContentId);

	List<CiamsPlanContentLink> selectList(Select params);

	List<CiamsPlanContentLink> selectCategoryList(CiamsPlanContentLinkDto params);

	int updateSortSn(CiamsPlanContentLinkDto.Modify modify);
	
}
