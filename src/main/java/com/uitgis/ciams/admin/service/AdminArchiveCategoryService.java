package com.uitgis.ciams.admin.service;

import java.util.List;

import com.uitgis.ciams.admin.dto.ArchiveCategoryDto.Find;
import com.uitgis.ciams.admin.dto.ArchiveCategoryDto.ListResult;
import com.uitgis.ciams.model.CiamsArchiveCategory;

public interface AdminArchiveCategoryService {

	List<ListResult> getList(Find param);

	CiamsArchiveCategory add(CiamsArchiveCategory param);

	CiamsArchiveCategory modify(CiamsArchiveCategory param);

	void changePriority(List<String> categoryIds);

	void remove(String categoryId);

}
