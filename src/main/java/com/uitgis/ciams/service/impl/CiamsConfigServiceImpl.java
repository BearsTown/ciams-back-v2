package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.user.dto.CiamsConfigDto;
import com.uitgis.ciams.enums.ConfigValueTypeEnum;
import com.uitgis.ciams.user.mapper.CiamsConfigMapper;
import com.uitgis.ciams.service.CiamsConfigService;
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
public class CiamsConfigServiceImpl implements CiamsConfigService{

	private final CiamsConfigMapper ciamsConfigMapper;

	private List<CiamsConfigDto.WithFile> configList;
	private List<CiamsConfigDto.WithFile> configTypeList;


	@PostConstruct
	public void getConfigList() {
		CiamsConfigDto.Find param = new CiamsConfigDto.Find();

		List<CiamsConfigDto.WithFile> list = ciamsConfigMapper.selectList(param);
		this.configList = list.stream()
				.filter(config -> !config.getConfType().equals("ROOT"))
				.collect(Collectors.toList());

		this.configTypeList = list.stream()
				.filter(config -> config.getConfType().equals("ROOT"))
				.collect(Collectors.toList());

	}

	public List<CiamsConfigDto.WithFile> getLoadConfigInfo(CiamsConfigDto.Find param) {
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

	public List<CiamsConfigDto.WithFile> getConfigTypeList() {
		return configTypeList;
	}

	public CiamsConfigDto.WithFile add(CiamsConfigDto.Add param) throws DuplicateKeyException {
		if (ValidUtil.empty(param.getConfValueType())) {
			param.setConfValueType(ConfigValueTypeEnum.STR.name());
		}

		Optional<CiamsConfigDto.WithFile> check = this.configList.stream()
		.filter(configId -> configId.getId().equals(param.getId()))
		.findFirst();

		Optional<CiamsConfigDto.WithFile> check2 = this.configTypeList.stream()
		.filter(configId -> configId.getId().equals(param.getId()))
		.findFirst();

		if(check.isPresent() && check2.isPresent()) {
			new DuplicateKeyException("키중복존재");
		}

		ciamsConfigMapper.insert(param);
		/* 로드 된 환경설정 정보 변경*/
		getConfigList();

		return ciamsConfigMapper.selectById(param.getId());
	}


	public List<CiamsConfigDto.WithFile> getConfigListByType(String confType) {
		return configList.stream()
				.filter(ciamsConfig -> ciamsConfig.getConfType().equals(confType))
				.collect(Collectors.toList());
	}

	public Optional<CiamsConfigDto.WithFile> getConfById(String confId) {
		return this.configList.stream()
				.filter(configId -> configId.getId().equals(confId))
				.findFirst();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public CiamsConfigDto.WithFile modify(CiamsConfigDto.Modify param) {

		ciamsConfigMapper.updateById(param);
		getConfigList();

		return ciamsConfigMapper.selectById(param.getId());
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteById(String id) {
		ciamsConfigMapper.deleteById(id);
		getConfigList();
	}

}
