package com.uitgis.ciams.service.impl.basic.urban;

import com.uitgis.ciams.dto.basic.urban.UrbanIndustryDto;
import com.uitgis.ciams.mapper.basic.urban.CiamsDataAttributeMapper;
import com.uitgis.ciams.mapper.basic.urban.CiamsDataColumnMapper;
import com.uitgis.ciams.mapper.basic.urban.CiamsDataGroupMapper;
import com.uitgis.ciams.mapper.basic.urban.CiamsDataMapper;
import com.uitgis.ciams.model.basic.urban.CiamsDataGroup;
import com.uitgis.ciams.service.basic.urban.UrbanIndustryDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UrbanIndustryDataServiceImpl implements UrbanIndustryDataService {
    private final CiamsDataMapper ciamsDataMapper;
    private final CiamsDataGroupMapper ciamsDataGroupMapper;
    private final CiamsDataColumnMapper ciamsDataColumnMapper;
    private final CiamsDataAttributeMapper ciamsDataAttributeMapper;


    /**
     * 도시공업지역 현황 데이터 그룹 목록
     *
     * @param parentId
     * @return
     */
    @Override
    public List<CiamsDataGroup> getDataGroups(int parentId) {
        return ciamsDataGroupMapper.findAllDataGroupsById(parentId);
    }


    /**
     * 도시공업지역 현황 데이터 메타데이터
     *
     * @param dataGroupId
     * @return
     */
    @Override
    public UrbanIndustryDto.MetaData getMetaData(int dataGroupId) {
        UrbanIndustryDto.MetaData metaData = UrbanIndustryDto.MetaData.builder()
                .years(ciamsDataMapper.findAllDataById(dataGroupId))
                .columns(ciamsDataColumnMapper.findAllDataColumnsById(dataGroupId))
                .attributes(ciamsDataAttributeMapper.findAllDataAttributesById(dataGroupId))
                .build();
        return metaData;
    }

}
