package com.uitgis.ciams.service;

import java.util.List;

import com.uitgis.ciams.user.dto.CiamsArchiveCategoryDto.Find;
import com.uitgis.ciams.user.dto.CiamsArchiveCategoryDto.ListResult;
import com.uitgis.ciams.model.CiamsArchiveCategory;

public interface CiamsArchiveCategoryService {

	List<ListResult> getList(Find param);

	CiamsArchiveCategory add(CiamsArchiveCategory param);

	CiamsArchiveCategory modify(CiamsArchiveCategory param);

	void changePriority(List<String> categoryIds);

	void remove(String categoryId);

}
