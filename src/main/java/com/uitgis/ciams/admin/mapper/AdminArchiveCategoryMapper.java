package com.uitgis.ciams.admin.mapper;

import com.uitgis.ciams.admin.dto.ArchiveCategoryDto.Find;
import com.uitgis.ciams.admin.dto.ArchiveCategoryDto.ListResult;
import com.uitgis.ciams.model.CiamsArchiveCategory;

import java.util.List;

public interface AdminArchiveCategoryMapper {
    CiamsArchiveCategory selectById(String categoryId);

    List<ListResult> selectList(Find param);

    int insert(CiamsArchiveCategory param);

    int updateById(CiamsArchiveCategory param);

    void deleteById(String categoryId);
}
