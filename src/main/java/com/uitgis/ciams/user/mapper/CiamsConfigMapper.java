package com.uitgis.ciams.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.uitgis.ciams.user.dto.CiamsConfigDto;
import com.uitgis.ciams.model.CiamsConfig;

@Mapper
public interface CiamsConfigMapper {
	int deleteById(String id);

    int insert(CiamsConfigDto.Add record);

    CiamsConfigDto.WithFile selectById(String id);

    int updateById(CiamsConfig record);

    List<CiamsConfigDto.WithFile> selectList(CiamsConfigDto.Find find);
}
