package com.uitgis.ciams.service;

import java.util.List;
import java.util.Optional;

import com.uitgis.ciams.dto.CiamsConfigDto;

public interface CiamsConfigService {

	List<CiamsConfigDto.WithFile> getLoadConfigInfo(CiamsConfigDto.Find param);

	List<CiamsConfigDto.WithFile> getConfigTypeList();

	List<CiamsConfigDto.WithFile> getConfigListByType(String confType);

	CiamsConfigDto.WithFile add(CiamsConfigDto.Add param);

	Optional<CiamsConfigDto.WithFile> getConfById(String id);

	CiamsConfigDto.WithFile modify(CiamsConfigDto.Modify param);

	void deleteById(String id);

}
