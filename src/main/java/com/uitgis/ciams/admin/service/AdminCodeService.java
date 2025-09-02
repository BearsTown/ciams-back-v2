package com.uitgis.ciams.admin.service;

import java.util.List;

import com.uitgis.ciams.admin.dto.CodeDto;
import com.uitgis.ciams.model.CiamsCode;

public interface AdminCodeService {
    List<CodeDto.Find> selectCodeListByParentCode(String parentCode);

    void addCode(CodeDto.Add add) throws Exception;

    CodeDto.Find selectCodeByCode(String code);

    void modify(CodeDto.Modify mod);

    void removeByCode(String code) throws Exception;

    void changeCodePriority(List<CiamsCode> codeList);
}
