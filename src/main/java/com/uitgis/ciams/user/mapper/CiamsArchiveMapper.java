package com.uitgis.ciams.user.mapper;

import com.uitgis.ciams.model.CiamsArchive;
import com.uitgis.ciams.user.dto.CiamsArchiveDto;

import java.util.List;

public interface CiamsArchiveMapper {
    CiamsArchive selectById(String id);

    int updateNumOfRead(String id);

    int count(CiamsArchiveDto.Find archiveParam);

    List<CiamsArchiveDto.ListResult> selectListWithFile(CiamsArchiveDto.Find param);

    CiamsArchiveDto.WithFile selectDetailById(String id);
}
