package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.Ciams1_1_3Dto;

import java.util.List;

public interface Ciams1_1_3Service {
	public List<Ciams1_1_3Dto.ItaData> getItaDatas(String sggCd);

	public List<Ciams1_1_3Dto.IndustryArea> getIndustryAreas(boolean exclude);

	public Ciams1_1_3Dto.Search.Result getItaResultDataList(Ciams1_1_3Dto.Search.Params params);

	public Ciams1_1_3Dto.IndustryStatus.Find.Result getIndustryStatus(Ciams1_1_3Dto.IndustryStatus.Find.Params params);
}
