package com.uitgis.ciams.service;

import java.util.List;

import com.uitgis.ciams.dto.CiamsPlanContentDto;
import com.uitgis.ciams.dto.CiamsPlanContentDto.SelectDetail;

public interface CiamsPlanContentService {

    public int insert(CiamsPlanContentDto.AddDetail params) throws Exception;

    public int update(CiamsPlanContentDto.Modify params) throws Exception;

    public int delete(String type, String id) throws Exception;

    public List<SelectDetail> selectList(CiamsPlanContentDto.Search params);

    public List<SelectDetail> selectAreaList(CiamsPlanContentDto.Search params);

	public int changePriority(String type, List<CiamsPlanContentDto.Priority> list);
}
