package com.uitgis.ciams.user.service.impl.classifyAnalysis;

import com.uitgis.ciams.user.dto.classifyAnalysis.ita.ClassItaDto;
import com.uitgis.ciams.user.mapper.classifyAnalysis.ClassItaMapper;
import com.uitgis.ciams.user.service.classifyAnalysis.ClassItaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClassItaServiceImpl implements ClassItaService {
	private final ClassItaMapper classItaMapper;


	/**
	 * 산업기반분석 결과
	 *
	 * @param zoneNo
	 * @return
	 */
	@Override
	public List<ClassItaDto.ItaResultData> getItaResultDataById(String zoneNo) {
		return classItaMapper.findAllItaResultDatasById(zoneNo);
	}

}
