package com.uitgis.ciams.mapper;

import com.uitgis.ciams.dto.CiamsArchiveDto;
import com.uitgis.ciams.model.CiamsArchive;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CiamsArchiveMapper {
    int deleteById(String id);

    int insert(CiamsArchiveDto.Add param);

    CiamsArchive selectById(String id);

    int updateById(CiamsArchive record);

    int updateNumOfRead(String id);

	int count(CiamsArchiveDto.Find archiveParam);

	List<CiamsArchiveDto.ListResult> selectListWithFile(CiamsArchiveDto.Find param);

	CiamsArchiveDto.WithFile selectDetailById(String id);

	int updateByIds(CiamsArchiveDto.ModifyAll param);

	int deleteByIds(@Param("ids") List<String> ids);
}
