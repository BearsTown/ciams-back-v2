package com.uitgis.ciams.user.service.impl;

import com.uitgis.ciams.user.dto.CiamsArchiveCategoryDto;
import com.uitgis.ciams.user.mapper.CiamsArchiveCategoryMapper;
import com.uitgis.ciams.user.service.CiamsArchiveCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsArchiveCategoryServiceImpl implements CiamsArchiveCategoryService {

    private final CiamsArchiveCategoryMapper ciamsArchiveCategoryMapper;


    @Override
    public List<CiamsArchiveCategoryDto.ListResult> getList(CiamsArchiveCategoryDto.Find param) {
        return ciamsArchiveCategoryMapper.selectList(param);
    }

}
