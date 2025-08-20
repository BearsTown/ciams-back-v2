package com.uitgis.ciams.user.service.basic.loc.status;

import com.uitgis.ciams.user.dto.basic.loc.status.ManufacturingDto;

public interface ManufacturingService {
    /**
     * 제조업별 사업체수/종사자수 정보
     *
     * @param params
     * @return
     */
    ManufacturingDto.Info getManufacturingInfo(ManufacturingDto.Find.Params params);


    /**
     * 제조업 유형별 현황 정보
     *
     * @return
     */
    ManufacturingDto.CategoryStatus.Info getCategoryStatusInfo();
}
