package com.uitgis.ciams.service.basic.loc.characteristic;

import com.uitgis.ciams.dto.basic.loc.characteristic.CharResultDto;
import com.uitgis.ciams.dto.basic.loc.characteristic.ItaDto;
import com.uitgis.ciams.dto.basic.loc.characteristic.StatusDto;

import java.util.List;

public interface ITAService {
	/**
	 * 산업기반분석
	 *
	 * @return
	 */
	ItaDto.Info.Find.Result getItaInfo();

	/**
	 * 산업기반분석 업종
	 *
	 * @param params
	 * @return
	 */
	ItaDto.Data.Search.Result getItaData(ItaDto.Data.Search.Params params);

	/**
	 * 산업 현황
	 *
	 * @param params
	 * @return
	 */
	StatusDto.IndustryStatus.Find.Result getIndustryStatus(StatusDto.IndustryStatus.Find.Params params);

	/**
	 * 산업특성분석 결과
	 *
	 * @param params
	 * @return
	 */
	CharResultDto.Char.Search.Result getItaResultDataList(CharResultDto.Char.Search.Params params);

	/**
	 * 인접 지자체 목록
	 *
	 * @param exclude
	 * @return
	 */
	List<ItaDto.Adjacent> getAdjacents(boolean exclude);
}
