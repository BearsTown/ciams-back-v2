package com.uitgis.ciams.service.impl.basic.loc.status;

import com.uitgis.ciams.dto.CiamsSourceGroupDto;
import com.uitgis.ciams.dto.basic.loc.CiamsBasicLocDescriptionDto;
import com.uitgis.ciams.dto.basic.loc.status.IndustrialDto;
import com.uitgis.ciams.mapper.CiamsSourceMapper;
import com.uitgis.ciams.mapper.basic.loc.CiamsBasicLocDescriptionMapper;
import com.uitgis.ciams.mapper.basic.loc.status.IndustrialMapper;
import com.uitgis.ciams.service.basic.loc.status.IndustrialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class IndustrialServiceImpl implements IndustrialService {
    private final IndustrialMapper industrialMapper;
    private final CiamsSourceMapper ciamsSourceMapper;
    private final CiamsBasicLocDescriptionMapper ciamsBasicLocDescriptionMapper;

    private final String Category = "산업현황분석";

    /**
     * 산업별 사업체수/종사자수 정보
     *
     * @param params
     * @return
     */
    @Override
    public IndustrialDto.Info getIndustrialInfo(IndustrialDto.Find.Params params) {
        CiamsSourceGroupDto.Find.Params sourcesParams = CiamsSourceGroupDto.Find.Params
                .builder()
                .category(Category)
                .targetId(params.getType())
                .build();

        CiamsBasicLocDescriptionDto.Find.Params descParams = CiamsBasicLocDescriptionDto.Find.Params
                .builder()
                .category(Category)
                .type(params.getType())
                .build();


        return IndustrialDto.Info.builder()
                .densities(industrialMapper.findAllDensities(params))
                .statuses(industrialMapper.findAllStatuses(params))
                .sources(ciamsSourceMapper.findAllSources(sourcesParams))
                .descriptions(ciamsBasicLocDescriptionMapper.findAllNotes(descParams))
                .build();
    }

}
