package com.uitgis.ciams.admin.service.impl;

import com.uitgis.ciams.admin.dto.ContentDto;
import com.uitgis.ciams.admin.dto.ContentMenuDto;
import com.uitgis.ciams.admin.mapper.AdminContentMapper;
import com.uitgis.ciams.admin.service.AdminContentService;
import com.uitgis.ciams.model.CiamsFile;
import com.uitgis.ciams.user.mapper.CiamsFileMapper;
import com.uitgis.ciams.util.FileUtil;
import com.uitgis.ciams.util.ValidUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service("AdminContentService")
public class AdminContentServiceImpl implements AdminContentService {
    private final CiamsFileMapper ciamsFileMapper;
    private final AdminContentMapper adminContentMapper;


    @Override
    public List<ContentDto.Find.Result> getContentList(ContentDto.Find.Params params) {
        return adminContentMapper.findAllContentsById(params);
    }


    @Override
    public ContentDto.Find.Result getContent(ContentDto.Find.Params params) {
        return adminContentMapper.findContentById(params);
    }

    @Override
    @Transactional
    public int addContent(ContentDto.Add params) {
        int result = adminContentMapper.insertContent(params);

        List<String> imageFileIds = params.getImageFileIds();

        if (ValidUtil.notEmpty(imageFileIds)) {
            List<CiamsFile> fileList = imageFileIds.stream()
                    .map(fId -> CiamsFile.builder()
                            .id(fId)
                            .status("completed")
                            .linkId(params.getId().toString())
                            .build())
                    .collect(Collectors.toList());

            ciamsFileMapper.updateFiles(fileList);
        }

        return result;
    }


    @Override
    @Transactional
    public int updateContent(ContentDto.Update params) {
        int result = adminContentMapper.updateContent(params);

        List<String> imageFileIds = params.getImageFileIds();

        if (ValidUtil.notEmpty(imageFileIds)) {
            List<CiamsFile> fileList = imageFileIds.stream()
                    .map(fId -> CiamsFile.builder()
                            .id(fId)
                            .status("completed")
//                            .linkId(params.getId().toString())
                            .build())
                    .collect(Collectors.toList());

            ciamsFileMapper.updateFiles(fileList);
        }

        return result;
    }


    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public int deleteContent(int id) {
        return deleteContent(List.of(id));
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public int deleteContent(List<Integer> ids) {
        if (ValidUtil.empty(ids)) {
            return 0;
        }

        List<String> linkIds = ids.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());

        if (ValidUtil.notEmpty(linkIds)) {
            List<CiamsFile> fileList = ciamsFileMapper.selectByLinkIds(linkIds);
            if (ValidUtil.notEmpty(fileList)) {
                FileUtil.deleteFiles(fileList);
                ciamsFileMapper.deleteFiles(fileList);
            }
        }

        return adminContentMapper.deleteContentByIds(ids);
    }


    @Override
    @Transactional
    public int updatePriority(ContentDto.Priority params) {
        return adminContentMapper.updatePriority(params);
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
