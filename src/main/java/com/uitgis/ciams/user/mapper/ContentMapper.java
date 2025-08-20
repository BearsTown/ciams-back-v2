package com.uitgis.ciams.user.mapper;

import com.uitgis.ciams.model.ContentMenu;
import com.uitgis.ciams.user.dto.ContentDto;
import com.uitgis.ciams.user.dto.ContentMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentMapper {
    List<ContentMenu> findALlContentMenuGroups();

    List<ContentMenuDto.Find.Result> findAllContentMenus(ContentMenuDto.Find.Params params);

    List<ContentDto.Find.Result> findAllContentsById(ContentDto.Find.Params params);
}
