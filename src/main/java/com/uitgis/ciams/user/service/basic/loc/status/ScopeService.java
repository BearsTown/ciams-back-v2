package com.uitgis.ciams.user.service.basic.loc.status;

import com.uitgis.ciams.user.dto.basic.loc.status.ScopeDto;

import java.util.List;

public interface ScopeService {
    /**
     * 산업의 범위 목록
     *
     * @return
     */
    List<ScopeDto.Data> getScopes();
}
