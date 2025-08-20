package com.uitgis.ciams.user.service.impl.basic.loc.status;

import com.uitgis.ciams.user.dto.CiamsSourceGroupDto;
import com.uitgis.ciams.user.dto.basic.loc.CiamsBasicLocDescriptionDto;
import com.uitgis.ciams.user.dto.basic.loc.status.DensityDto;
import com.uitgis.ciams.user.mapper.CiamsSourceMapper;
import com.uitgis.ciams.user.mapper.basic.loc.CiamsBasicLocDescriptionMapper;
import com.uitgis.ciams.user.mapper.basic.loc.status.DensityMapper;
import com.uitgis.ciams.user.service.basic.loc.status.DensityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DensityServiceImpl implements DensityService {
	private final DensityMapper densityMapper;
	private final CiamsSourceMapper ciamsSourceMapper;
	private final CiamsBasicLocDescriptionMapper ciamsBasicLocDescriptionMapper;


	private final String Category = "산업현황분석";

	/**
	 * 사업체 밀도변화 정보
	 *
	 * @param type
	 * @return
	 */
	@Override
	public DensityDto.DensityInfo getDensityInfo(String type) {
		CiamsSourceGroupDto.Find.Params sourcesParams = CiamsSourceGroupDto.Find.Params
				.builder()
				.category(Category)
				.targetId(type)
				.build();

		CiamsBasicLocDescriptionDto.Find.Params descParams = CiamsBasicLocDescriptionDto.Find.Params
				.builder()
				.category(Category)
				.type(type)
				.build();

		return DensityDto.DensityInfo
				.builder()
				.densities(densityMapper.findDensityByType(type))
				.sources(ciamsSourceMapper.findAllSources(sourcesParams))
				.descriptions(ciamsBasicLocDescriptionMapper.findAllNotes(descParams))
				.build();
	}

}
