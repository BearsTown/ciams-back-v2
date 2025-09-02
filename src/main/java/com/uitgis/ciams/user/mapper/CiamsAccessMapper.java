package com.uitgis.ciams.user.mapper;

import com.uitgis.ciams.user.dto.CiamsAccessDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsAccessMapper {
	List<CiamsAccessDto.MenuAccess> selectMenuAccess(String loginId);
}
