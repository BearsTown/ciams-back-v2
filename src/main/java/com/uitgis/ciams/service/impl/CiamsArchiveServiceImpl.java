package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.model.CiamsFile;
import com.uitgis.ciams.service.CiamsArchiveService;
import com.uitgis.ciams.service.CiamsCommonService;
import com.uitgis.ciams.user.dto.CiamsArchiveDto;
import com.uitgis.ciams.user.dto.PaginationDto;
import com.uitgis.ciams.user.mapper.CiamsArchiveMapper;
import com.uitgis.ciams.user.mapper.CiamsFileMapper;
import com.uitgis.ciams.util.FileUtil;
import com.uitgis.ciams.util.PageUtil;
import com.uitgis.ciams.util.ValidUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


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

		List<String> imageFileIds = param.getImageFileIds();

		if (ValidUtil.notEmpty(imageFileIds)) {
			List<CiamsFile> fileList = imageFileIds.stream()
					.map(fId -> CiamsFile.builder()
							.id(fId)
							.status("completed")
							.linkId(param.getArchiveId())
							.build())
					.collect(Collectors.toList());

			ciamsFileMapper.updateFiles(fileList);
		}

		return getArchiveWithFile(archiveId);
	}


	@Transactional
	public CiamsArchiveDto.WithFile modify(CiamsArchiveDto.Modify param) {
		String archiveId = param.getArchiveId();
		ciamsArchiveMapper.updateById(param);

		List<String> imageFileIds = param.getImageFileIds();

		if (ValidUtil.notEmpty(imageFileIds)) {
			List<CiamsFile> fileList = imageFileIds.stream()
					.map(fId -> CiamsFile.builder()
							.id(fId)
							.status("completed")
							.build())
					.collect(Collectors.toList());

			ciamsFileMapper.updateFiles(fileList);
		}

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
		List<String> linkIds = new ArrayList<>();
		linkIds.add(archiveId);

		if (ValidUtil.notEmpty(linkIds)) {
			List<CiamsFile> fileList = ciamsFileMapper.selectByLinkIds(linkIds);
			if (ValidUtil.notEmpty(fileList)) {
				FileUtil.deleteFiles(fileList);
				ciamsFileMapper.deleteFiles(fileList);
			}
		}

		return ciamsArchiveMapper.deleteById(archiveId);
	}


	private CiamsArchiveDto.WithFile getArchiveWithFile(String archiveId) {
		CiamsArchiveDto.WithFile detail = ciamsArchiveMapper.selectDetailById(archiveId);
		return detail;
	}


}
