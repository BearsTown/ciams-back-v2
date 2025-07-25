package com.uitgis.ciams.service.impl.basic.loc.status;

import com.uitgis.ciams.dto.basic.loc.status.DensityDto;
import com.uitgis.ciams.mapper.basic.loc.status.DensityMapper;
import com.uitgis.ciams.service.basic.loc.status.DensityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DensityServiceImpl implements DensityService {
	private final DensityMapper densityMapper;


	/**
	 * 사업체 밀도변화 정보
	 *
	 * @param type
	 * @return
	 */
	@Override
	public DensityDto.DensityInfo getDensityInfo(String type) {
		return densityMapper.findDensityInfoByType(type);
	}

}
