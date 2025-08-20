package com.uitgis.ciams.user.service.impl;

import com.uitgis.ciams.model.ContentMenu;
import com.uitgis.ciams.user.dto.ContentDto;
import com.uitgis.ciams.user.dto.ContentMenuDto;
import com.uitgis.ciams.user.mapper.ContentMapper;
import com.uitgis.ciams.user.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ContentServiceImpl implements ContentService {

    private final ContentMapper contentMapper;


    @Override
    public List<ContentMenu> getContentMenuGroupList() {
        return contentMapper.findALlContentMenuGroups();
    }


    @Override
    public List<ContentMenuDto.Find.Result> getContentMenuList(ContentMenuDto.Find.Params params) {
        List<ContentMenuDto.Find.Result> allNodes = contentMapper.findAllContentMenus(params);

        List<ContentMenuDto.Find.Result> roots = allNodes.stream()
                .filter(node -> Objects.equals(node.getParentId(), params.getParentId()))
                .collect(Collectors.toList());

        for (ContentMenuDto.Find.Result root : roots) {
            setChildren(root, allNodes);
        }

        return roots;
    }


    @Override
    public List<ContentDto.Find.Result> getContentList(ContentDto.Find.Params params) {
        return contentMapper.findAllContentsById(params);
    }


    private void setChildren(ContentMenuDto.Find.Result parent, List<ContentMenuDto.Find.Result> allNodes) {
        List<ContentMenuDto.Find.Result> children = allNodes.stream()
                .filter(node -> parent.getId().equals(node.getParentId()))
                .collect(Collectors.toList());

        parent.setChildren(children);

        for (ContentMenuDto.Find.Result child : children) {
            setChildren(child, allNodes);
        }
    }
}
