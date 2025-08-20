package com.uitgis.ciams.user.service.impl.basic.loc.status;

import com.uitgis.ciams.user.dto.CiamsSourceGroupDto;
import com.uitgis.ciams.user.dto.basic.loc.CiamsBasicLocDescriptionDto;
import com.uitgis.ciams.user.dto.basic.loc.status.ManufacturingDto;
import com.uitgis.ciams.user.mapper.CiamsSourceMapper;
import com.uitgis.ciams.user.mapper.basic.loc.CiamsBasicLocDescriptionMapper;
import com.uitgis.ciams.user.mapper.basic.loc.status.ManufacturingMapper;
import com.uitgis.ciams.user.service.basic.loc.status.ManufacturingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ManufacturingServiceImpl implements ManufacturingService {
	private final ManufacturingMapper manufacturingMapper;
	private final CiamsSourceMapper ciamsSourceMapper;
	private final CiamsBasicLocDescriptionMapper ciamsBasicLocDescriptionMapper;

	private final String Category = "산업현황분석";

	/**
	 * 제조업별 사업체수/종사자수 정보
	 *
	 * @param params
	 * @return
	 */
	@Override
	public ManufacturingDto.Info getManufacturingInfo(ManufacturingDto.Find.Params params) {
		CiamsSourceGroupDto.Find.Params sourcesParams = CiamsSourceGroupDto.Find.Params
				.builder()
				.category(Category)
				.targetId(params.getType())
				.build();

		CiamsBasicLocDescriptionDto.Find.Params descParams = CiamsBasicLocDescriptionDto.Find.Params
				.builder()
				.category(Category)
				.type(params.getType())
				.build();

		return ManufacturingDto.Info.builder()
				.densities(manufacturingMapper.findAllDensities(params))
				.statuses(manufacturingMapper.findAllStatuses(params))
				.increases(manufacturingMapper.findAllIncreases(params))
				.sources(ciamsSourceMapper.findAllSources(sourcesParams))
				.descriptions(ciamsBasicLocDescriptionMapper.findAllNotes(descParams))
				.build();
	}


	/**
	 * 제조업 유형별 현황 정보
	 *
	 * @return
	 */
	@Override
	public ManufacturingDto.CategoryStatus.Info getCategoryStatusInfo() {
		CiamsSourceGroupDto.Find.Params sourcesParams = CiamsSourceGroupDto.Find.Params
				.builder()
				.category(Category)
				.targetId("B007")
				.build();

		CiamsBasicLocDescriptionDto.Find.Params descParams = CiamsBasicLocDescriptionDto.Find.Params
				.builder()
				.category(Category)
				.type("B007")
				.build();

		return ManufacturingDto.CategoryStatus.Info.builder()
				.statuses(manufacturingMapper.findAllCategoryStatuses())
				.categoryGroups(manufacturingMapper.findAllCategoryGroups())
				.sources(ciamsSourceMapper.findAllSources(sourcesParams))
				.descriptions(ciamsBasicLocDescriptionMapper.findAllNotes(descParams))
				.build();
	}

}
