package com.uitgis.ciams.user.service;

import com.uitgis.ciams.user.dto.CiamsConfigDto;

import java.util.List;
import java.util.Optional;

public interface CiamsConfigService {
    List<CiamsConfigDto.WithFile> getLoadConfigInfo(CiamsConfigDto.Find param);

    Optional<CiamsConfigDto.WithFile> getConfById(String id);
}
