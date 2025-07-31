package com.uitgis.ciams.service.impl.basic.loc.characteristic;

import com.uitgis.ciams.dto.CiamsSourceGroupDto;
import com.uitgis.ciams.dto.PaginationDto;
import com.uitgis.ciams.dto.basic.loc.CiamsBasicLocDescriptionDto;
import com.uitgis.ciams.dto.basic.loc.characteristic.CharResultDto;
import com.uitgis.ciams.dto.basic.loc.characteristic.ItaDto;
import com.uitgis.ciams.dto.basic.loc.characteristic.StatusDto;
import com.uitgis.ciams.mapper.CiamsSourceMapper;
import com.uitgis.ciams.mapper.basic.loc.CiamsBasicLocDescriptionMapper;
import com.uitgis.ciams.mapper.basic.loc.characteristic.ITAMapper;
import com.uitgis.ciams.service.basic.loc.characteristic.ITAService;
import com.uitgis.ciams.util.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ITAServiceImpl implements ITAService {
    private final ITAMapper ITAMapper;
    private final CiamsSourceMapper ciamsSourceMapper;
    private final CiamsBasicLocDescriptionMapper ciamsBasicLocDescriptionMapper;

    private final String Category = "산업특성분석";

    /**
     * 산업기반분석
     *
     * @return
     */
    @Override
    public ItaDto.Info.Find.Result getItaInfo() {
        String type = "산업기반분석";

        CiamsSourceGroupDto.Find.Params sourcesParams = CiamsSourceGroupDto.Find.Params
                .builder()
                .category(Category)
                .targetId(type)
                .build();

        CiamsBasicLocDescriptionDto.Find.Params descParams = CiamsBasicLocDescriptionDto.Find.Params
                .builder()
                .category(Category)
                .type(type)
                .build();

        return ItaDto.Info.Find.Result
                .builder()
                .sources(ciamsSourceMapper.findAllSources(sourcesParams))
                .descriptions(ciamsBasicLocDescriptionMapper.findAllNotes(descParams))
                .build();
    }


    /**
     * 산업기반분석 업종
     *
     * @param params
     * @return
     */
    @Override
    public ItaDto.Data.Search.Result getItaData(ItaDto.Data.Search.Params params) {
        int totalCount = ITAMapper.countItaData(params);

        PaginationDto page = PageUtil.setTotalCount(params, totalCount);

        List<ItaDto.Data.Search.Row> rows = ITAMapper.findAllItaDatas(params);

        return ItaDto.Data.Search.Result.builder()
                .page(page)
                .list(rows)
                .build();
    }


    /**
     * 산업 현황
     *
     * @param params
     * @return
     */
    @Override
    public StatusDto.IndustryStatus.Find.Result getIndustryStatus(StatusDto.IndustryStatus.Find.Params params) {
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

        return StatusDto.IndustryStatus.Find.Result
                .builder()
                .industryReps(ITAMapper.findAllIndustryReps(params))
                .sources(ciamsSourceMapper.findAllSources(sourcesParams))
                .descriptions(ciamsBasicLocDescriptionMapper.findAllNotes(descParams))
                .build();
    }


    /**
     * 산업특성분석 결과
     *
     * @param params
     * @return
     */
    @Override
    public CharResultDto.Char.Search.Result getItaResultDataList(CharResultDto.Char.Search.Params params) {
        int totalCount = ITAMapper.countItaResultData(params);

        PaginationDto page = PageUtil.setTotalCount(params, totalCount);

        List<CharResultDto.Char.Search.Row> rows = ITAMapper.findAllItaResultDatas(params);

        return CharResultDto.Char.Search.Result.builder()
                .page(page)
                .list(rows)
                .build();
    }


    /**
     * 인접 지자체 목록
     *
     * @param exclude
     * @return
     */
    @Override
    public List<ItaDto.Adjacent> getAdjacents(boolean exclude) {
        return ITAMapper.findAllAdjacents(exclude);
    }

}
