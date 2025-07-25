package com.uitgis.ciams.service.impl.basic.loc.status;

import com.uitgis.ciams.dto.basic.loc.status.ManufacturingDto;
import com.uitgis.ciams.mapper.basic.loc.status.ManufacturingMapper;
import com.uitgis.ciams.service.basic.loc.status.ManufacturingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ManufacturingServiceImpl implements ManufacturingService {
	private final ManufacturingMapper manufacturingMapper;


	/**
	 * 제조업별 사업체수/종사자수 정보
	 *
	 * @param params
	 * @return
	 */
	@Override
	public ManufacturingDto.Info getManufacturingInfo(ManufacturingDto.Find.Params params) {
		return ManufacturingDto.Info.builder()
				.densities(manufacturingMapper.findAllDensities(params))
				.statuses(manufacturingMapper.findAllStatuses(params))
				.increases(manufacturingMapper.findAllIncreases(params))
//				.notes()
				.build();
	}


	/**
	 * 제조업 유형별 현황 정보
	 *
	 * @return
	 */
	@Override
	public ManufacturingDto.CategoryStatus.Info getCategoryStatusInfo() {
		return ManufacturingDto.CategoryStatus.Info.builder()
				.statuses(manufacturingMapper.findAllCategoryStatuses())
				.categoryGroups(manufacturingMapper.findAllCategoryGroups())
				.build();
	}

}
