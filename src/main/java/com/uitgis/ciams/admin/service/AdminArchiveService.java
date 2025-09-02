package com.uitgis.ciams.admin.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.uitgis.ciams.admin.dto.ArchiveDto;
import com.uitgis.ciams.model.CiamsFile;

public interface AdminArchiveService {
	int getTotal(ArchiveDto.Find archiveParam);

	ArchiveDto.WithFile add(ArchiveDto.Add param);

	Map<String, Object> getListWithFile(ArchiveDto.Find param);

	ArchiveDto.WithFile modify(ArchiveDto.Modify param);

	List<CiamsFile> getFilesById(String archiveId);

	int removeId(String archiveId) throws IOException;

	ArchiveDto.WithFile getDetailById(String archiveId);

	void modifyByIds(ArchiveDto.ModifyAll param);

	List<CiamsFile> getArchiveFilesByArchiveIds(List<String> ids);

	void deleteByIds(ArchiveDto.ModifyAll param);
}
