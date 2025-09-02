package com.uitgis.ciams.user.service;

import com.uitgis.ciams.user.dto.CiamsArchiveDto;

import java.util.Map;

public interface CiamsArchiveService {
    Map<String, Object> getListWithFile(CiamsArchiveDto.Find param);

    CiamsArchiveDto.WithFile getDetailById(String archiveId);
}
