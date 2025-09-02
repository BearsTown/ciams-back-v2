package com.uitgis.ciams.user.service.impl;

import com.uitgis.ciams.user.dto.CiamsConfigDto;
import com.uitgis.ciams.user.mapper.CiamsConfigMapper;
import com.uitgis.ciams.user.service.CiamsConfigService;
import com.uitgis.ciams.util.ValidUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsConfigServiceImpl implements CiamsConfigService {
    private final CiamsConfigMapper ciamsConfigMapper;

    private List<CiamsConfigDto.WithFile> configList;


    @PostConstruct
    public void getConfigList() {
        CiamsConfigDto.Find param = new CiamsConfigDto.Find();

        List<CiamsConfigDto.WithFile> list = ciamsConfigMapper.selectList(param);
        this.configList = list.stream()
                .filter(config -> !config.getConfType().equals("ROOT"))
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


    public Optional<CiamsConfigDto.WithFile> getConfById(String confId) {
        return this.configList.stream()
                .filter(configId -> configId.getId().equals(confId))
                .findFirst();
    }

}
