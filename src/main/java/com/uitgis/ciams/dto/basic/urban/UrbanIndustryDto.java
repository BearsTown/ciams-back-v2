package com.uitgis.ciams.dto.basic.urban;

import com.uitgis.ciams.model.basic.urban.CiamsData;
import com.uitgis.ciams.model.basic.urban.CiamsDataAttribute;
import com.uitgis.ciams.model.basic.urban.CiamsDataColumn;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class UrbanIndustryDto {

    @Getter
    @Setter
    @Builder
    public static class MetaData {
        private List<CiamsData> years;
        private List<CiamsDataColumn> columns;
        private List<CiamsDataAttribute> attributes;
    }

}
