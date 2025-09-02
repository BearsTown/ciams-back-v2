package com.uitgis.ciams.admin.service.impl;

import com.uitgis.ciams.admin.mapper.AdminCodeMapper;
import com.uitgis.ciams.admin.service.AdminCodeService;
import com.uitgis.ciams.exception.CustomException;
import com.uitgis.ciams.model.CiamsCode;
import com.uitgis.ciams.admin.dto.CodeDto;
import com.uitgis.ciams.admin.dto.CodeDto.Modify;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
//@Service("ciamsCodeService")
@Service
public class AdminCodeServiceImpl implements AdminCodeService {
    private final AdminCodeMapper adminCodeMapper;


    @Override
    public List<CodeDto.Find> selectCodeListByParentCode(String parentCode) {
        return adminCodeMapper.selectCodeListByParentCode(parentCode);
    }


    @Override
    public void addCode(CodeDto.Add add) throws Exception {
        CiamsCode ciamsCode = new CiamsCode();
        BeanUtils.copyProperties(add, ciamsCode);

        CiamsCode _ciamsCode = adminCodeMapper.selectCodeByCode(ciamsCode.getCode());
        if (_ciamsCode != null) {
            throw new DuplicateKeyException("중복된 코드값이 존재합니다.");
        } else {

            List<CodeDto.Find> list = adminCodeMapper.selectCodeListByParentCode(ciamsCode.getParentCode());
            int sortMax = list.stream().mapToInt(a -> a.getSortSn() == null ? 0 : a.getSortSn()).max().orElse(0);
            ciamsCode.setSortSn(sortMax + 1);
            adminCodeMapper.insertCode(ciamsCode);
        }
    }


    @Override
    public CodeDto.Find selectCodeByCode(String code) {
        return adminCodeMapper.selectCodeByCode(code);
    }


    @Override
    public void modify(Modify mod) {
        CiamsCode ciamsCode = new CiamsCode();
        BeanUtils.copyProperties(mod, ciamsCode);

        adminCodeMapper.updateCode(ciamsCode);
    }


    @Override
    public void removeByCode(String code) throws Exception {
        List<CodeDto.Find> childCodes = adminCodeMapper.selectCodeListByParentCode(code);

        if (childCodes.size() > 0) {
            throw new CustomException("하위 트리 코드값이 존재합니다.");
        }

        adminCodeMapper.deleteByCode(code);
    }


    @Override
    @Transactional
    public void changeCodePriority(List<CiamsCode> codeList) {
        for (CiamsCode code : codeList) {
            adminCodeMapper.updateCode(code);
        }
    }

}
