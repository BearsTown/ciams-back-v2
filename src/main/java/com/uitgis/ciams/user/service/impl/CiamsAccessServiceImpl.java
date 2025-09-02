package com.uitgis.ciams.user.service.impl;

import com.uitgis.ciams.service.CiamsCommonService;
import com.uitgis.ciams.user.dto.CiamsAccessDto;
import com.uitgis.ciams.user.mapper.CiamsAccessMapper;
import com.uitgis.ciams.user.service.CiamsAccessService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CiamsAccessServiceImpl implements CiamsAccessService {
    private final CiamsAccessMapper ciamsAccessMapper;
    private final CiamsCommonService ciamsCommonService;


    @Override
    public List<CiamsAccessDto.MenuAccess> getMenuAccessList() throws Exception {
        String loginId = ciamsCommonService.getUsername();
        return ciamsAccessMapper.selectMenuAccess(loginId);
    }

}
