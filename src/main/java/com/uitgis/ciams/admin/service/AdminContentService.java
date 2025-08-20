package com.uitgis.ciams.admin.service;

import com.uitgis.ciams.admin.dto.ContentDto;

import java.util.List;

public interface AdminContentService {
    List<ContentDto.Find.Result> getContentList(ContentDto.Find.Params params);

    ContentDto.Find.Result getContent(ContentDto.Find.Params params);

    int addContent(ContentDto.Add params);

    int updateContent(ContentDto.Update params);

    int deleteContent(int id);

    int deleteContent(List<Integer> ids);

    int updatePriority(ContentDto.Priority params);
}
