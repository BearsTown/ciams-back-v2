package com.uitgis.mis.mapper;


import com.uitgis.mis.model.Dtsource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MisMapper {
    public List<Dtsource> selectDtsource(String userId);
}


