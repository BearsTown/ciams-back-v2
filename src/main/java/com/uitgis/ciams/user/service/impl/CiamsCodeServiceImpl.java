package com.uitgis.ciams.user.service.impl;

import com.uitgis.ciams.admin.dto.CodeDto;
import com.uitgis.ciams.model.CiamsCode;
import com.uitgis.ciams.user.dto.CiamsCodeDto;
import com.uitgis.ciams.user.mapper.CiamsCodeMapper;
import com.uitgis.ciams.user.service.CiamsCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CiamsCodeServiceImpl implements CiamsCodeService {
    private final CiamsCodeMapper ciamsCodeMapper;


    @Override
    public List<CiamsCodeDto.Find> selectCodeListByParentCode(String parentCode) {
        return ciamsCodeMapper.selectCodeListByParentCode(parentCode);
    }

    @Override
    public CiamsCodeDto.Find selectCodeByCode(String code) {
        return ciamsCodeMapper.selectCodeByCode(code);
    }


    @Override
    public List<CiamsCode> selectCodeSublist(CiamsCodeDto.Sub sub) {
        return ciamsCodeMapper.selectCodeSublist(sub);
    }

}
