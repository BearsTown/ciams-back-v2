package com.uitgis.ciams.admin.mapper;

import java.util.List;

import com.uitgis.ciams.admin.dto.CodeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.uitgis.ciams.model.CiamsCode;

@Mapper
public interface AdminCodeMapper {
    List<CodeDto.Find> selectCodeListByParentCode(String parentCode);

    CodeDto.Find selectCodeByCode(String code);

    int insertCode(CiamsCode code);

    int deleteByCode(String code);

    int updateCode(CiamsCode code);

    void deleteByCodes(@Param("codeList") List<String> codeList);
}
