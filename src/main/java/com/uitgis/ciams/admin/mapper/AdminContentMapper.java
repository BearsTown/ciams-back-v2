package com.uitgis.ciams.admin.mapper;

import com.uitgis.ciams.admin.dto.ContentDto;
import com.uitgis.ciams.admin.dto.ContentMenuDto;
import com.uitgis.ciams.model.ContentMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminContentMapper {
    List<ContentDto.Find.Result> findAllContentsById(ContentDto.Find.Params params);

    ContentDto.Find.Result findContentById(ContentDto.Find.Params params);

    List<Integer> findAllIdsByMenuId(int menuId);

    int insertContent(ContentDto.Add params);

    int updateContent(ContentDto.Update params);

    int deleteContentByIds(List<Integer> ids);

    int updatePriority(ContentDto.Priority params);
}
