package com.uitgis.ciams.admin.service.impl;

import com.uitgis.ciams.admin.dto.ContentDto;
import com.uitgis.ciams.admin.dto.ContentMenuDto;
import com.uitgis.ciams.admin.mapper.AdminContentMapper;
import com.uitgis.ciams.admin.mapper.AdminContentMenuMapper;
import com.uitgis.ciams.admin.service.AdminContentMenuService;
import com.uitgis.ciams.admin.service.AdminContentService;
import com.uitgis.ciams.model.ContentMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service("AdminContentMenuService")
public class AdminContentMenuServiceImpl implements AdminContentMenuService {
    private final AdminContentMapper adminContentMapper;
    private final AdminContentMenuMapper adminContentMenuMapper;

    private final AdminContentService adminContentService;


    @Override
    public List<ContentMenu> getContentMenuGroupList() {
        return adminContentMenuMapper.findALlContentMenuGroups();
    }


    @Override
    public List<ContentMenuDto.Find.Result> getContentMenuList(ContentMenuDto.Find.Params params) {
        List<ContentMenuDto.Find.Result> allNodes = adminContentMenuMapper.findAllContentMenus(params);

        List<ContentMenuDto.Find.Result> roots = allNodes.stream()
                .filter(node -> Objects.equals(node.getParentId(), params.getParentId()))
                .collect(Collectors.toList());

        for (ContentMenuDto.Find.Result root : roots) {
            setChildren(root, allNodes);
        }

        return roots;
    }


    @Override
    public ContentMenu getContentMenu(int id) {
        return adminContentMenuMapper.findContentMenuById(id);
    }

    @Override
    @Transactional
    public int addContentMenu(ContentMenuDto.Add params) {
        return adminContentMenuMapper.insertContentMenu(params);
    }


    @Override
    @Transactional
    public int updateContentMenu(ContentMenuDto.Update params) {
        return adminContentMenuMapper.updateContentMenu(params);
    }


    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public int deleteContentMenu(int id) {
        List<Integer> contentIdList = adminContentMapper.findAllIdsByMenuId(id);

        adminContentService.deleteContent(contentIdList);

        return adminContentMenuMapper.deleteContentMenu(id);
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
