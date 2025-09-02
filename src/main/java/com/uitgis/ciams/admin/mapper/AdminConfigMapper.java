package com.uitgis.ciams.admin.mapper;

import com.uitgis.ciams.admin.dto.ConfigDto;
import com.uitgis.ciams.model.CiamsConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminConfigMapper {
    int deleteById(String id);

    int insert(ConfigDto.Add record);

    ConfigDto.WithFile selectById(String id);

    int updateById(CiamsConfig record);

    List<ConfigDto.WithFile> selectList(ConfigDto.Find find);
}
