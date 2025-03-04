package com.uitgis.ciams.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.uitgis.ciams.dto.CiamsArchiveDto;
import com.uitgis.ciams.model.CiamsFile;

public interface CiamsArchiveService {
	int getTotal(CiamsArchiveDto.Find archiveParam);

	CiamsArchiveDto.WithFile add(CiamsArchiveDto.Add param);

	Map<String, Object> getListWithFile(CiamsArchiveDto.Find param);

	CiamsArchiveDto.WithFile modify(CiamsArchiveDto.Modify param);

	List<CiamsFile> getFilesById(String archiveId);

	int removeId(String archiveId) throws IOException;

	CiamsArchiveDto.WithFile getDetailById(String archiveId);

	void modifyByIds(CiamsArchiveDto.ModifyAll param);

	List<CiamsFile> getArchiveFilesByArchiveIds(List<String> ids);

	void deleteByIds(CiamsArchiveDto.ModifyAll param);
}
