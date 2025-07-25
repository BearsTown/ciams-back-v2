package com.uitgis.ciams.service.impl.basic.loc.status;

import com.uitgis.ciams.dto.basic.loc.status.TechLQDto;
import com.uitgis.ciams.mapper.basic.loc.status.TechLQMapper;
import com.uitgis.ciams.service.basic.loc.status.TechLQService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TechLQServiceImpl implements TechLQService {
    private final TechLQMapper techLQMapper;


    /**
     * 집적도 높은 기술
     *
     * @param params
     * @return
     */
    @Override
    public TechLQDto.HighTech.Find.Result getHighTech(TechLQDto.HighTech.Find.Params params) {
        return techLQMapper.findAllHighTech(params);
    }


    /**
     * 기술업종목록
     *
     * @param params
     * @return
     */
    @Override
    public List<TechLQDto.TechLQ.Find.Result> getTechLQs(TechLQDto.TechLQ.Find.Params params) {
        return techLQMapper.findAllTechLQs(params);
    }

}
