package com.uitgis.ciams.service;

import com.uitgis.ciams.model.CiamsFile;
import com.uitgis.ciams.user.dto.CiamsFileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CiamsFileService {
    List<CiamsFileDto.TempFile> uploadTempFiles(List<MultipartFile> files) throws Exception;

    List<CiamsFileDto.TempFile> uploadTempFiles(List<MultipartFile> files, String typeCode) throws Exception;

    CiamsFile selectById(String id);

    int insertFiles(List<CiamsFile> inserts) throws IOException;

    int updateFiles(List<CiamsFile> updates) throws IOException;

    int updateFilesSortSn(String linkId) throws IOException;

    int deleteFilesByLinkIds(List<String> linkIds) throws IOException;

    int deleteFilesByIds(List<String> ids) throws IOException;

    int searchCount(CiamsFileDto.Search.Params params);

    CiamsFileDto.Search.Result search(CiamsFileDto.Search.Params params);

    int uploadFiles(List<MultipartFile> files, String typeCode, String linkId) throws Exception;

    List<CiamsFile> uploadEditorImages(CiamsFileDto.EditorImage.Upload params) throws Exception;
}
