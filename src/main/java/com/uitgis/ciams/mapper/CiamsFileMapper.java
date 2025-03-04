package com.uitgis.ciams.mapper;

import com.uitgis.ciams.dto.CiamsFileDto;
import com.uitgis.ciams.model.CiamsFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsFileMapper {
    public int insertFiles(List<CiamsFile> files);

    public int updateFiles(List<CiamsFile> files);

    public int updateFilesSortSn(List<CiamsFile> files);

    public int deleteFiles(List<CiamsFile> files);

    public CiamsFile selectById(String id);

    public List<CiamsFile> selectByIds(List<String> ids);

    public List<CiamsFile> selectByLinkIds(List<String> linkIds);

    public int searchCount(CiamsFileDto.Search.Params params);

    public List<CiamsFileDto.Search.Row> search(CiamsFileDto.Search.Params params);

}
