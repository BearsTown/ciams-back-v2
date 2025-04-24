package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.Ciams1_1_3Dto;
import com.uitgis.ciams.mapper.Ciams1_1_3Mapper;
import com.uitgis.ciams.service.Ciams1_1_3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class Ciams1_1_3ServiceImpl implements Ciams1_1_3Service {
	private final Ciams1_1_3Mapper ciams1_1_3Mapper;

	@Override
	public List<Ciams1_1_3Dto.ItaData> getItaDatas(String sggCd) {
		return ciams1_1_3Mapper.selectItaDatas(sggCd);
	}

	@Override
	public List<Ciams1_1_3Dto.IndustryArea> getIndustryAreas(boolean exclude) {
		return ciams1_1_3Mapper.selectIndustryAreas(exclude);
	}

	@Override
	public List<Ciams1_1_3Dto.ItaResultData> getItaResultDatas(String sggCd) {
		return ciams1_1_3Mapper.selectItaResultDatas(sggCd);
	}

	@Override
	public Ciams1_1_3Dto.IndustryStatus.Find.Result getIndustryStatus(Ciams1_1_3Dto.IndustryStatus.Find.Params params) {
		List<Ciams1_1_3Dto.IndustryStatusData> statusDatas = ciams1_1_3Mapper.selectIndustryStatusDatas(params);
		List<Ciams1_1_3Dto.IndustryRep> reps = ciams1_1_3Mapper.selectIndustryReps(params);

        return Ciams1_1_3Dto.IndustryStatus.Find.Result
				.builder()
				.IndustryStatusDatas(statusDatas)
				.industryReps(reps)
				.build();
	}
}
