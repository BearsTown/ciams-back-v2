package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.CiamsIncentiveDetailsDto;
import com.uitgis.ciams.dto.CiamsMenu1StepCDetailsDto;
import com.uitgis.ciams.dto.CiamsMenu1StepCDto;
import com.uitgis.ciams.dto.CiamsPlanAreaDto;
import com.uitgis.ciams.dto.PaginationDto;
import com.uitgis.ciams.service.CiamsMenu1StepCService;
import com.uitgis.ciams.util.PageUtil;
import com.uitgis.gis.mapper.GisMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CiamsCiamsMenu1StepCServiceImpl implements CiamsMenu1StepCService {
    private final GisMapper gismapper;

    @Override
    public CiamsMenu1StepCDto.Search.Result getPlanAreaList(CiamsMenu1StepCDto.Search.Params params) {
        int totalCount = gismapper.selectPlanAreaCount(params);

        PaginationDto page = PageUtil.setTotalCount(params, totalCount);

        List<CiamsMenu1StepCDto.Search.Row> rows = gismapper.getPlanAreaList(params);

        CiamsMenu1StepCDto.Search.Result result = CiamsMenu1StepCDto.Search.Result.builder().page(page).list(rows).build();

        return result;
    }

    @Override
    public CiamsMenu1StepCDto.Details.Result getPlanArea(CiamsMenu1StepCDto.Search.Detail detail) {
        return null;
    }

    @Override
    public CiamsMenu1StepCDetailsDto.Overview.Find.Result getOverView(CiamsMenu1StepCDetailsDto.Overview.Find.Params params) {
        return gismapper.selectOverView(params);
    }
}
