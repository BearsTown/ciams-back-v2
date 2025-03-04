package com.uitgis.ciams.service;

import java.util.List;

import com.uitgis.ciams.dto.CiamsPlanContentLinkDto;
import com.uitgis.ciams.model.CiamsPlanContentLink;

public interface CiamsPlanContentLinkService {

    public int insert(CiamsPlanContentLinkDto.Add params) throws Exception;

    public int update(List<CiamsPlanContentLinkDto.Modify> params) throws Exception;

    public int delete(CiamsPlanContentLinkDto.Delete params) throws Exception;

	public List<CiamsPlanContentLink> selectList(CiamsPlanContentLinkDto.Select params);

	public int insertArea(String planContentId);

}
