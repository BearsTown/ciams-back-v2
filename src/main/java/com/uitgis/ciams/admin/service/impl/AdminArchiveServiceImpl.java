package com.uitgis.ciams.admin.service.impl;

import com.uitgis.ciams.admin.dto.ArchiveDto;
import com.uitgis.ciams.admin.mapper.AdminArchiveMapper;
import com.uitgis.ciams.admin.service.AdminArchiveService;
import com.uitgis.ciams.model.CiamsFile;
import com.uitgis.ciams.service.CiamsCommonService;
import com.uitgis.ciams.user.dto.PaginationDto;
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
public class AdminArchiveServiceImpl implements AdminArchiveService {
	private final CiamsCommonService ciamsCommonService;
	private final AdminArchiveMapper adminArchiveMapper;
	private final CiamsFileMapper ciamsFileMapper;


	public int getTotal(ArchiveDto.Find param) {
		if (!ciamsCommonService.isAdmin()) {
			param.setHidden(false);
		}
		return adminArchiveMapper.count(param);
	}


	public Map<String, Object> getListWithFile(ArchiveDto.Find param) {
		if (!ciamsCommonService.isAdmin()) {
			param.setHidden(false);
		}

		int cnt = adminArchiveMapper.count(param);

		PaginationDto page = PageUtil.setTotalCount(param, cnt);
		List<ArchiveDto.ListResult> list = adminArchiveMapper.selectListWithFile(param);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("list", list);
		resultMap.put("page", page);

		return resultMap;
	}


	public ArchiveDto.WithFile getDetailById(String archiveId) {
		// 아카이브 정보 조회 시 조회 수 +1
//		adminArchiveMapper.updateNumOfRead(archiveId);
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
	public ArchiveDto.WithFile add(ArchiveDto.Add param) {
		String archiveId = UUID.randomUUID().toString();
		param.setArchiveId(archiveId);
		adminArchiveMapper.insert(param);

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
	public ArchiveDto.WithFile modify(ArchiveDto.Modify param) {
		String archiveId = param.getArchiveId();
		adminArchiveMapper.updateById(param);

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

	public void modifyByIds(ArchiveDto.ModifyAll param) {
		adminArchiveMapper.updateByIds(param);
	}


	public List<CiamsFile> getArchiveFilesByArchiveIds(List<String> ids) {
		return ciamsFileMapper.selectByLinkIds(ids);
	}


	public void deleteByIds(ArchiveDto.ModifyAll param) {
		adminArchiveMapper.deleteByIds(param.getIds());
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

		return adminArchiveMapper.deleteById(archiveId);
	}


	private ArchiveDto.WithFile getArchiveWithFile(String archiveId) {
		ArchiveDto.WithFile detail = adminArchiveMapper.selectDetailById(archiveId);
		return detail;
	}


}
