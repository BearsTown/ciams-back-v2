package com.uitgis.ciams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.uitgis.ciams.dto.CiamsPlanContentDetailDto.DeleteParam;
import com.uitgis.ciams.model.CiamsPlanContentDetail;

@Mapper
public interface CiamsPlanContentDetailMapper {

	List<CiamsPlanContentDetail> select(CiamsPlanContentDetail model);

	int insert(CiamsPlanContentDetail model);

	int update(CiamsPlanContentDetail model);

	int delete(DeleteParam parma);
}
