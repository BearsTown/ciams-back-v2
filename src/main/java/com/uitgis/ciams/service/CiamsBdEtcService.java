package com.uitgis.ciams.service;

import java.util.List;

import com.uitgis.ciams.dto.CiamsBdEtcDto;

public interface CiamsBdEtcService {

	public List<CiamsBdEtcDto.Search.Row> selectBdEtc(CiamsBdEtcDto.Search.param param);

}
