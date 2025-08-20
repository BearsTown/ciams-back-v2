package com.uitgis.ciams.user.service;

import com.uitgis.ciams.user.dto.ContentDto;
import com.uitgis.ciams.user.dto.ContentMenuDto;
import com.uitgis.ciams.model.ContentMenu;

import java.util.List;

public interface ContentService {
    List<ContentMenu> getContentMenuGroupList();

    List<ContentMenuDto.Find.Result> getContentMenuList(ContentMenuDto.Find.Params params);

    List<ContentDto.Find.Result> getContentList(ContentDto.Find.Params params);
}
