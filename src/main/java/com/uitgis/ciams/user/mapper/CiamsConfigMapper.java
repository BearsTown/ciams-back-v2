package com.uitgis.ciams.user.mapper;

import com.uitgis.ciams.user.dto.CiamsConfigDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsConfigMapper {
    CiamsConfigDto.WithFile selectById(String id);

    List<CiamsConfigDto.WithFile> selectList(CiamsConfigDto.Find find);
}
