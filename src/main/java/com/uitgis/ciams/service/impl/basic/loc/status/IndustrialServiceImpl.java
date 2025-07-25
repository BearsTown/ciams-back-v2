package com.uitgis.ciams.service.impl.basic.loc.status;

import com.uitgis.ciams.dto.basic.loc.status.IndustrialDto;
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


    /**
     * 산업별 사업체수/종사자수 정보
     *
     * @param params
     * @return
     */
    @Override
    public IndustrialDto.Info getIndustrialInfo(IndustrialDto.Find.Params params) {
        return IndustrialDto.Info.builder()
                .densities(industrialMapper.findAllDensities(params))
                .statuses(industrialMapper.findAllStatuses(params))
//				.notes()
                .build();
    }

}
