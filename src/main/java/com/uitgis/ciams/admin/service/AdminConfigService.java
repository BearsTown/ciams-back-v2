package com.uitgis.ciams.admin.service;

import com.uitgis.ciams.admin.dto.ConfigDto;

import java.util.List;
import java.util.Optional;

public interface AdminConfigService {
	List<ConfigDto.WithFile> getLoadConfigInfo(ConfigDto.Find param);

	List<ConfigDto.WithFile> getConfigTypeList();

	List<ConfigDto.WithFile> getConfigListByType(String confType);

	ConfigDto.WithFile add(ConfigDto.Add param);

	Optional<ConfigDto.WithFile> getConfById(String id);

	ConfigDto.WithFile modify(ConfigDto.Modify param);

	void deleteById(String id);
}
