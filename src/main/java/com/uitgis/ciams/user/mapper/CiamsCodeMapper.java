package com.uitgis.ciams.user.mapper;

import com.uitgis.ciams.admin.dto.CodeDto;
import com.uitgis.ciams.model.CiamsCode;
import com.uitgis.ciams.user.dto.CiamsCodeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsCodeMapper {
    List<CiamsCodeDto.Find> selectCodeListByParentCode(String parentCode);

    CiamsCodeDto.Find selectCodeByCode(String code);

    List<CiamsCode> selectCodeSublist(CiamsCodeDto.Sub sub);
}
