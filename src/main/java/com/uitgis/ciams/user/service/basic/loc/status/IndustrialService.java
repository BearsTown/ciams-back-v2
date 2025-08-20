package com.uitgis.ciams.user.service.basic.loc.status;

import com.uitgis.ciams.user.dto.basic.loc.status.IndustrialDto;

public interface IndustrialService {
	/**
	 * 산업별 사업체수/종사자수 정보
	 *
	 * @param params
	 * @return
	 */
	IndustrialDto.Info getIndustrialInfo(IndustrialDto.Find.Params params);
}
