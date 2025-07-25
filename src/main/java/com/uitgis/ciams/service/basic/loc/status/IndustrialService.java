package com.uitgis.ciams.service.basic.loc.status;

import com.uitgis.ciams.dto.basic.loc.status.IndustrialDto;

public interface IndustrialService {
	/**
	 * 산업별 사업체수/종사자수 정보
	 *
	 * @param params
	 * @return
	 */
	IndustrialDto.Info getIndustrialInfo(IndustrialDto.Find.Params params);
}
