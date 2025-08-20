package com.uitgis.ciams.service;

import java.util.List;

import com.uitgis.ciams.user.dto.CiamsCodeDto;
import com.uitgis.ciams.model.CiamsCode;

public interface CiamsCodeService {

    List<CiamsCodeDto.Find> selectCodeListByParentCode(String parentCode);

    void addCode(CiamsCodeDto.Add add) throws Exception;

    CiamsCodeDto.Find selectCodeByCode(String code);

    void modify(CiamsCodeDto.Modify mod);

    void removeByCode(String code) throws Exception;

    void changeCodePriority(List<CiamsCode> codeList);

    public List<CiamsCode> selectCodeSublist(CiamsCodeDto.Sub sub);
}
