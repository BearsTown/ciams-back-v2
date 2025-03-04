package com.uitgis.ciams.mapper;

import java.util.List;

import com.uitgis.ciams.dto.CiamsCodeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.uitgis.ciams.model.CiamsCode;

@Mapper
public interface CiamsCodeMapper {

    List<CiamsCodeDto.Find> selectCodeListByParentCode(String parentCode);

    CiamsCodeDto.Find selectCodeByCode(String code);

    int insertCode(CiamsCode code);

    int deleteByCode(String code);

    int updateCode(CiamsCode code);

    public void deleteByCodes(@Param("codeList") List<String> codeList);

    public List<CiamsCode> selectCodeSublist(CiamsCodeDto.Sub sub);
}
