package com.uitgis.ciams.user.mapper.basic.loc;

import com.uitgis.ciams.user.dto.basic.loc.CiamsBasicLocDescriptionDto;
import com.uitgis.ciams.model.basic.loc.CiamsBasicLocDescription;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsBasicLocDescriptionMapper {
    /**
     * 노트 목록
     *
     * @param params
     * @return
     */
    List<CiamsBasicLocDescription> findAllNotes(CiamsBasicLocDescriptionDto.Find.Params params);
}
