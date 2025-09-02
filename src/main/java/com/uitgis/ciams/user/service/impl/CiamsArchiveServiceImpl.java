package com.uitgis.ciams.user.service.impl;

import com.uitgis.ciams.model.CiamsFile;
import com.uitgis.ciams.service.CiamsCommonService;
import com.uitgis.ciams.user.dto.CiamsArchiveDto;
import com.uitgis.ciams.user.dto.PaginationDto;
import com.uitgis.ciams.user.mapper.CiamsArchiveMapper;
import com.uitgis.ciams.user.mapper.CiamsFileMapper;
import com.uitgis.ciams.user.service.CiamsArchiveService;
import com.uitgis.ciams.util.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsArchiveServiceImpl implements CiamsArchiveService {
    private final CiamsCommonService ciamsCommonService;
    private final CiamsArchiveMapper ciamsArchiveMapper;
    private final CiamsFileMapper ciamsFileMapper;


    public Map<String, Object> getListWithFile(CiamsArchiveDto.Find param) {
        if (!ciamsCommonService.isAdmin()) {
            param.setHidden(false);
        }

        int cnt = ciamsArchiveMapper.count(param);

        PaginationDto page = PageUtil.setTotalCount(param, cnt);
        List<CiamsArchiveDto.ListResult> list = ciamsArchiveMapper.selectListWithFile(param);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", list);
        resultMap.put("page", page);

        return resultMap;
    }


    public CiamsArchiveDto.WithFile getDetailById(String archiveId) {
        // 아카이브 정보 조회 시 조회 수 +1
        ciamsArchiveMapper.updateNumOfRead(archiveId);
        return getArchiveWithFile(archiveId);
    }


    /**
     * 아카이브에 등록된 첨부 파일 목록 조회
     */
    public List<CiamsFile> getFilesById(String archiveId) {
        List<String> ids = new ArrayList<String>();
        ids.add(archiveId);
        return ciamsFileMapper.selectByLinkIds(ids);
    }


    private CiamsArchiveDto.WithFile getArchiveWithFile(String archiveId) {
        CiamsArchiveDto.WithFile detail = ciamsArchiveMapper.selectDetailById(archiveId);
        return detail;
    }


}
