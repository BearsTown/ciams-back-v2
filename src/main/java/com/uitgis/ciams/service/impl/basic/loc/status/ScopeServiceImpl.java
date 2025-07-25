package com.uitgis.ciams.service.impl.basic.loc.status;

import com.uitgis.ciams.dto.basic.loc.status.ScopeDto;
import com.uitgis.ciams.mapper.basic.loc.status.ScopeMapper;
import com.uitgis.ciams.service.basic.loc.status.ScopeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ScopeServiceImpl implements ScopeService {
    private final ScopeMapper scopeMapper;


    /**
     * 산업의 범위 목록
     *
     * @return
     */
    @Override
    public List<ScopeDto.Data> getScopes() {
        return scopeMapper.findAllScopes();
    }
}
