package com.uitgis.ciams.admin.mapper;

import com.uitgis.ciams.admin.dto.ContentMenuDto;
import com.uitgis.ciams.model.ContentMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminContentMenuMapper {
    List<ContentMenu> findALlContentMenuGroups();

    List<ContentMenuDto.Find.Result> findAllContentMenus(ContentMenuDto.Find.Params params);

    ContentMenu findContentMenuById(int id);

    int insertContentMenu(ContentMenuDto.Add params);

    int updateContentMenu(ContentMenuDto.Update params);

    int deleteContentMenu(int id);
}
