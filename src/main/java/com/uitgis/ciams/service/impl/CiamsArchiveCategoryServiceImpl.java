package com.uitgis.ciams.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uitgis.ciams.user.dto.CiamsArchiveCategoryDto.Find;
import com.uitgis.ciams.user.dto.CiamsArchiveCategoryDto.ListResult;
import com.uitgis.ciams.user.mapper.CiamsArchiveCategoryMapper;
import com.uitgis.ciams.model.CiamsArchiveCategory;
import com.uitgis.ciams.service.CiamsArchiveCategoryService;
import com.uitgis.ciams.service.CiamsCommonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsArchiveCategoryServiceImpl implements CiamsArchiveCategoryService{

	private final CiamsArchiveCategoryMapper ciamsArchiveCategoryMapper;
	private final CiamsCommonService ciamsCommonService;

	public CiamsArchiveCategory getById(String categoryId) {
		return ciamsArchiveCategoryMapper.selectById(categoryId);
	}

	@Override
	public List<ListResult> getList(Find param) {
		return ciamsArchiveCategoryMapper.selectList(param);
	}

	@Override
	public CiamsArchiveCategory add(CiamsArchiveCategory param) {
		String categoryId = UUID.randomUUID().toString();
		param.setCategoryId(categoryId);
		int rtn = ciamsArchiveCategoryMapper.insert(param);
		return getById(categoryId);
	}

	@Override
	public CiamsArchiveCategory modify(CiamsArchiveCategory param) {
		int rtn = ciamsArchiveCategoryMapper.updateById(param);
		return getById(param.getCategoryId());
	}

	@Override
	public void changePriority(List<String> categoryIds) {

		for (int i=0; i< categoryIds.size(); i++) {
			CiamsArchiveCategory category =new CiamsArchiveCategory();
			category.setCategoryId(categoryIds.get(i));
			category.setSortSn(i+1);
			category.setChgUser(ciamsCommonService.getUsername());

			ciamsArchiveCategoryMapper.updateById(category);
		}
	}

	@Override
	public void remove(String categoryId) {
		ciamsArchiveCategoryMapper.deleteById(categoryId);
	}

}
