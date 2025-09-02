package com.uitgis.ciams.user.service;

import com.uitgis.ciams.user.dto.CiamsArchiveCategoryDto;

import java.util.List;

public interface CiamsArchiveCategoryService {
    List<CiamsArchiveCategoryDto.ListResult> getList(CiamsArchiveCategoryDto.Find param);
}
