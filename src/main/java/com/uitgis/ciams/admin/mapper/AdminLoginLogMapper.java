package com.uitgis.ciams.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.uitgis.ciams.user.dto.CiamsLoginLogDto;
import com.uitgis.ciams.user.dto.CiamsLoginLogDto.Find;
import com.uitgis.ciams.user.dto.CiamsLoginLogDto.GroupFind;
import com.uitgis.ciams.model.CiamsLoginLog;

@Mapper
public interface AdminLoginLogMapper {
	int selectCnt(CiamsLoginLogDto.Find dto);

	List<CiamsLoginLog> selectList(CiamsLoginLogDto.Find dto);

	int insert(CiamsLoginLogDto.Add dto);

	List<GroupFind> selectGroupList(Find find);
}
