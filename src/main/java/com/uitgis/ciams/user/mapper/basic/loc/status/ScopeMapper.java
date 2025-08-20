package com.uitgis.ciams.user.mapper.basic.loc.status;

import com.uitgis.ciams.user.dto.basic.loc.status.ScopeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScopeMapper {
    /**
     * 산업의 범위 목록
     *
     * @return
     */
    List<ScopeDto.Data> findAllScopes();
}
