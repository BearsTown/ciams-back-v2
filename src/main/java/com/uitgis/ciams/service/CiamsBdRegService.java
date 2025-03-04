package com.uitgis.ciams.service;

import java.util.List;

import com.uitgis.ciams.dto.CiamsBdRegDto;

public interface CiamsBdRegService {

	public List<CiamsBdRegDto.Search.Row> selectBdReg(CiamsBdRegDto.Search.param param);

}
