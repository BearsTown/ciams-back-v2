package com.uitgis.ciams.mapper;

import java.util.List;

import com.uitgis.ciams.dto.CiamsArchiveCategoryDto.Find;
import com.uitgis.ciams.dto.CiamsArchiveCategoryDto.ListResult;
import com.uitgis.ciams.model.CiamsArchiveCategory;

public interface CiamsArchiveCategoryMapper {

	public CiamsArchiveCategory selectById(String categoryId);

	public List<ListResult> selectList(Find param);

	public int insert(CiamsArchiveCategory param);

	public int updateById(CiamsArchiveCategory param);

	public void deleteById(String categoryId);

}
