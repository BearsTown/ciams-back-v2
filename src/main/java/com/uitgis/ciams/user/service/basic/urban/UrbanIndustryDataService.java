package com.uitgis.ciams.user.service.basic.urban;

import com.uitgis.ciams.user.dto.basic.urban.CiamsBasicUrbanDto;
import com.uitgis.ciams.user.dto.basic.urban.UrbanIndustryDto;
import com.uitgis.ciams.model.basic.urban.CiamsDataGroup;

import java.util.List;

public interface UrbanIndustryDataService {
    List<CiamsDataGroup> getDataGroups(int parentId);

    UrbanIndustryDto.MetaData getMetaData(int dataGroupId);

    CiamsBasicUrbanDto.Info.Find.Result getDataInfo(CiamsBasicUrbanDto.Info.Find.Params params);
}
