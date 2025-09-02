package com.uitgis.ciams.admin.mapper;

import com.uitgis.ciams.admin.dto.ArchiveDto;
import com.uitgis.ciams.model.CiamsArchive;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminArchiveMapper {
    int deleteById(String id);

    int insert(ArchiveDto.Add param);

    CiamsArchive selectById(String id);

    int updateById(CiamsArchive record);

    int updateNumOfRead(String id);

	int count(ArchiveDto.Find archiveParam);

	List<ArchiveDto.ListResult> selectListWithFile(ArchiveDto.Find param);

	ArchiveDto.WithFile selectDetailById(String id);

	int updateByIds(ArchiveDto.ModifyAll param);

	int deleteByIds(@Param("ids") List<String> ids);
}
