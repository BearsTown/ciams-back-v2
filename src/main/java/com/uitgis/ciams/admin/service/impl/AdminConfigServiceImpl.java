package com.uitgis.ciams.admin.service.impl;

import com.uitgis.ciams.admin.dto.ConfigDto;
import com.uitgis.ciams.admin.mapper.AdminConfigMapper;
import com.uitgis.ciams.admin.service.AdminConfigService;
import com.uitgis.ciams.enums.ConfigValueTypeEnum;
import com.uitgis.ciams.util.ValidUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminConfigServiceImpl implements AdminConfigService {
	private final AdminConfigMapper adminConfigMapper;

	private List<ConfigDto.WithFile> configList;
	private List<ConfigDto.WithFile> configTypeList;


	@PostConstruct
	public void getConfigList() {
		ConfigDto.Find param = new ConfigDto.Find();

		List<ConfigDto.WithFile> list = adminConfigMapper.selectList(param);
		this.configList = list.stream()
				.filter(config -> !config.getConfType().equals("ROOT"))
				.collect(Collectors.toList());

		this.configTypeList = list.stream()
				.filter(config -> config.getConfType().equals("ROOT"))
				.collect(Collectors.toList());

	}

	public List<ConfigDto.WithFile> getLoadConfigInfo(ConfigDto.Find param) {
		return this.configList.stream()
				.filter(config -> {
					if (ValidUtil.notEmpty(param.getConfType())) {
						return config.getConfType().equals(param.getConfType());
					}
					return true;
				})
				.filter(config -> {
					if (ValidUtil.notEmpty(param.getUsed())) {
						return config.getUsed() == param.getUsed();
					}
					return true;
				})
				.filter(config -> {
					if (ValidUtil.notEmpty(param.getId())) {
						return config.getId().equals(param.getId());
					}
					return true;
				})
				.filter(config -> {
					if (ValidUtil.notEmpty(param.getConfName())) {
						return config.getConfName().equals(param.getConfName());
					}
					return true;
				})
				.collect(Collectors.toList());
	}

	public List<ConfigDto.WithFile> getConfigTypeList() {
		return configTypeList;
	}

	public ConfigDto.WithFile add(ConfigDto.Add param) throws DuplicateKeyException {
		if (ValidUtil.empty(param.getConfValueType())) {
			param.setConfValueType(ConfigValueTypeEnum.STR.name());
		}

		Optional<ConfigDto.WithFile> check = this.configList.stream()
		.filter(configId -> configId.getId().equals(param.getId()))
		.findFirst();

		Optional<ConfigDto.WithFile> check2 = this.configTypeList.stream()
		.filter(configId -> configId.getId().equals(param.getId()))
		.findFirst();

		if(check.isPresent() && check2.isPresent()) {
			new DuplicateKeyException("키중복존재");
		}

		adminConfigMapper.insert(param);
		/* 로드 된 환경설정 정보 변경*/
		getConfigList();

		return adminConfigMapper.selectById(param.getId());
	}


	public List<ConfigDto.WithFile> getConfigListByType(String confType) {
		return configList.stream()
				.filter(ciamsConfig -> ciamsConfig.getConfType().equals(confType))
				.collect(Collectors.toList());
	}

	public Optional<ConfigDto.WithFile> getConfById(String confId) {
		return this.configList.stream()
				.filter(configId -> configId.getId().equals(confId))
				.findFirst();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ConfigDto.WithFile modify(ConfigDto.Modify param) {

		adminConfigMapper.updateById(param);
		getConfigList();

		return adminConfigMapper.selectById(param.getId());
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteById(String id) {
		adminConfigMapper.deleteById(id);
		getConfigList();
	}

}
