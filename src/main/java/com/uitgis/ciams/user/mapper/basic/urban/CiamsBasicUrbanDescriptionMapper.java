package com.uitgis.ciams.user.mapper.basic.urban;

import com.uitgis.ciams.user.dto.basic.urban.CiamsBasicUrbanDto;
import com.uitgis.ciams.model.basic.urban.CiamsBasicUrbanDescription;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsBasicUrbanDescriptionMapper {
    /**
     * 노트 목록
     *
     * @param params
     * @return
     */
    List<CiamsBasicUrbanDescription> findAllNotes(CiamsBasicUrbanDto.Info.Find.Params params);
}
