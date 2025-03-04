package com.uitgis.ciams.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uitgis.ciams.dto.CiamsArchiveDto;
import com.uitgis.ciams.dto.PaginationDto;
import com.uitgis.ciams.mapper.CiamsArchiveMapper;
import com.uitgis.ciams.mapper.CiamsFileMapper;
import com.uitgis.ciams.model.CiamsFile;
import com.uitgis.ciams.service.CiamsArchiveService;
import com.uitgis.ciams.service.CiamsCommonService;
import com.uitgis.ciams.util.PageUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsArchiveServiceImpl implements CiamsArchiveService{
	private final CiamsCommonService ciamsCommonService;
	private final CiamsArchiveMapper ciamsArchiveMapper;
	private final CiamsFileMapper ciamsFileMapper;

	public int getTotal(CiamsArchiveDto.Find param) {
		if (!ciamsCommonService.isAdmin()) {
			param.setHidden(false);
		}
		return ciamsArchiveMapper.count(param);
	}


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
	 *
	 */
	public List<CiamsFile> getFilesById(String archiveId) {
		List<String> ids = new ArrayList<String>();
		ids.add(archiveId);
		return ciamsFileMapper.selectByLinkIds(ids);
	}


	@Transactional
	public CiamsArchiveDto.WithFile add(CiamsArchiveDto.Add param) {
		String archiveId = UUID.randomUUID().toString();
		param.setArchiveId(archiveId);
		ciamsArchiveMapper.insert(param);

		return getArchiveWithFile(archiveId);
	}


	@Transactional
	public CiamsArchiveDto.WithFile modify(CiamsArchiveDto.Modify param) {
		String archiveId = param.getArchiveId();
		ciamsArchiveMapper.updateById(param);

		return getArchiveWithFile(archiveId);
	}

	public void modifyByIds(CiamsArchiveDto.ModifyAll param) {
		ciamsArchiveMapper.updateByIds(param);
	}


	public List<CiamsFile> getArchiveFilesByArchiveIds(List<String> ids) {
		return ciamsFileMapper.selectByLinkIds(ids);
	}


	public void deleteByIds(CiamsArchiveDto.ModifyAll param) {
		ciamsArchiveMapper.deleteByIds(param.getIds());
	}


	public int removeId(String archiveId) throws IOException {
		return ciamsArchiveMapper.deleteById(archiveId);
	}


	private CiamsArchiveDto.WithFile getArchiveWithFile(String archiveId) {
		CiamsArchiveDto.WithFile detail = ciamsArchiveMapper.selectDetailById(archiveId);
		return detail;
	}


}
