package com.uitgis.ciams.service.basic.loc.status;

import com.uitgis.ciams.dto.basic.loc.status.DensityDto;

public interface DensityService {
	/**
	 * 사업체 밀도변화 정보
	 *
	 * @param type
	 * @return
	 */
	DensityDto.DensityInfo getDensityInfo(String type);
}
