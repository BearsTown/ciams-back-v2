package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.Menu1StepCDetailsDto;

public interface Menu1StepCService {
    public Menu1StepCDetailsDto.Overview.Find.Result getOverView(Menu1StepCDetailsDto.Overview.Find.Params params);
}
