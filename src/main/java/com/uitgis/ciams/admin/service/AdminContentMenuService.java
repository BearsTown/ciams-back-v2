package com.uitgis.ciams.admin.service;

import com.uitgis.ciams.admin.dto.ContentMenuDto;
import com.uitgis.ciams.model.ContentMenu;

import java.util.List;

public interface AdminContentMenuService {
    List<ContentMenu> getContentMenuGroupList();

    List<ContentMenuDto.Find.Result> getContentMenuList(ContentMenuDto.Find.Params params);

    ContentMenu getContentMenu(int id);

    int addContentMenu(ContentMenuDto.Add params);

    int updateContentMenu(ContentMenuDto.Update params);

    int deleteContentMenu(int id);
}
