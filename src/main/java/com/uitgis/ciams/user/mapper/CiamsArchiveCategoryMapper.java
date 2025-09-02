package com.uitgis.ciams.user.mapper;


import com.uitgis.ciams.user.dto.CiamsArchiveCategoryDto;

import java.util.List;

public interface CiamsArchiveCategoryMapper {

    List<CiamsArchiveCategoryDto.ListResult> selectList(CiamsArchiveCategoryDto.Find param);

}
