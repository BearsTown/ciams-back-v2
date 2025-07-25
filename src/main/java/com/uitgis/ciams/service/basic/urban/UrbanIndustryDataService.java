package com.uitgis.ciams.service.basic.urban;

import com.uitgis.ciams.dto.basic.urban.UrbanIndustryDto;
import com.uitgis.ciams.model.basic.urban.CiamsDataGroup;

import java.util.List;

public interface UrbanIndustryDataService {
    List<CiamsDataGroup> getDataGroups(int parentId);

    UrbanIndustryDto.MetaData getMetaData(int dataGroupId);
}
