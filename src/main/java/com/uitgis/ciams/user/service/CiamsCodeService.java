package com.uitgis.ciams.user.service;

import com.uitgis.ciams.admin.dto.CodeDto;
import com.uitgis.ciams.model.CiamsCode;
import com.uitgis.ciams.user.dto.CiamsCodeDto;

import java.util.List;

public interface CiamsCodeService {
    List<CiamsCodeDto.Find> selectCodeListByParentCode(String parentCode);

    CiamsCodeDto.Find selectCodeByCode(String code);

    List<CiamsCode> selectCodeSublist(CiamsCodeDto.Sub sub);
}
