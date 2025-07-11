package com.uitgis.gis.mapper;

import com.uitgis.ciams.dto.Menu1StepCDetailsDto;
import com.uitgis.ciams.dto.Menu2ZoneDetailsDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GisMapper {
	public Menu1StepCDetailsDto.Overview.Find.Result selectOverView(Menu1StepCDetailsDto.Overview.Find.Params params);

	public Menu2ZoneDetailsDto.Overview.Find.Result selectMenu2Sub1OverView(Menu2ZoneDetailsDto.Overview.Find.Params params);
}
