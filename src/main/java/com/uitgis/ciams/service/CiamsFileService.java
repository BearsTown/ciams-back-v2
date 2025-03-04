package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.CiamsFileDto;
import com.uitgis.ciams.model.CiamsFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CiamsFileService {
    public List<CiamsFileDto.TempFile> uploadTempFiles(List<MultipartFile> files) throws Exception;

    public List<CiamsFileDto.TempFile> uploadTempFiles(List<MultipartFile> files, String typeCode) throws Exception;

    public CiamsFile selectById(String id);

    public int insertFiles(List<CiamsFile> inserts) throws IOException;

    public int updateFiles(List<CiamsFile> updates) throws IOException;

    public int updateFilesSortSn(String linkId) throws IOException;

    public int deleteFilesByLinkIds(List<String> linkIds) throws IOException;

    public int deleteFilesByIds(List<String> ids) throws IOException;

    public int searchCount(CiamsFileDto.Search.Params params);

    public CiamsFileDto.Search.Result search(CiamsFileDto.Search.Params params);

    public int uploadFiles(List<MultipartFile> files, String typeCode, String linkId) throws Exception;

}
