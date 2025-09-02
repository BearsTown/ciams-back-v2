package com.uitgis.ciams.admin.service.impl;

import com.uitgis.ciams.admin.dto.ArchiveCategoryDto.Find;
import com.uitgis.ciams.admin.dto.ArchiveCategoryDto.ListResult;
import com.uitgis.ciams.admin.mapper.AdminArchiveCategoryMapper;
import com.uitgis.ciams.admin.service.AdminArchiveCategoryService;
import com.uitgis.ciams.model.CiamsArchiveCategory;
import com.uitgis.ciams.service.CiamsCommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminArchiveCategoryServiceImpl implements AdminArchiveCategoryService {

	private final CiamsCommonService ciamsCommonService;
	private final AdminArchiveCategoryMapper adminArchiveCategoryMapper;


	public CiamsArchiveCategory getById(String categoryId) {
		return adminArchiveCategoryMapper.selectById(categoryId);
	}


	@Override
	public List<ListResult> getList(Find param) {
		return adminArchiveCategoryMapper.selectList(param);
	}


	@Override
	public CiamsArchiveCategory add(CiamsArchiveCategory param) {
		String categoryId = UUID.randomUUID().toString();
		param.setCategoryId(categoryId);
		int rtn = adminArchiveCategoryMapper.insert(param);
		return getById(categoryId);
	}


	@Override
	public CiamsArchiveCategory modify(CiamsArchiveCategory param) {
		int rtn = adminArchiveCategoryMapper.updateById(param);
		return getById(param.getCategoryId());
	}


	@Override
	public void changePriority(List<String> categoryIds) {

		for (int i=0; i< categoryIds.size(); i++) {
			CiamsArchiveCategory category =new CiamsArchiveCategory();
			category.setCategoryId(categoryIds.get(i));
			category.setSortSn(i+1);
			category.setChgUser(ciamsCommonService.getUsername());

			adminArchiveCategoryMapper.updateById(category);
		}
	}


	@Override
	public void remove(String categoryId) {
		adminArchiveCategoryMapper.deleteById(categoryId);
	}

}
