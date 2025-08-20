package com.uitgis.ciams.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uitgis.ciams.user.dto.CiamsCodeDto;
import com.uitgis.ciams.user.dto.CiamsCodeDto.Modify;
import com.uitgis.ciams.exception.CustomException;
import com.uitgis.ciams.user.mapper.CiamsCodeMapper;
import com.uitgis.ciams.model.CiamsCode;
import com.uitgis.ciams.service.CiamsCodeService;

@Service("ciamsCodeService")
public class CiamsCodeServiceImpl implements CiamsCodeService {

    @Autowired
    CiamsCodeMapper ciamsCodeMapper;

    @Override
    public List<CiamsCodeDto.Find> selectCodeListByParentCode(String parentCode) {
        return ciamsCodeMapper.selectCodeListByParentCode(parentCode);
    }

    @Override
    public void addCode(CiamsCodeDto.Add add) throws Exception {
        CiamsCode ciamsCode = new CiamsCode();
        BeanUtils.copyProperties(add, ciamsCode);

        CiamsCode _ciamsCode = ciamsCodeMapper.selectCodeByCode(ciamsCode.getCode());
        if (_ciamsCode != null) {
            throw new DuplicateKeyException("중복된 코드값이 존재합니다.");
        } else {

            List<CiamsCodeDto.Find> list = ciamsCodeMapper.selectCodeListByParentCode(ciamsCode.getParentCode());
            int sortMax = list.stream().mapToInt(a -> a.getSortSn() == null ? 0 : a.getSortSn()).max().orElse(0);
            ciamsCode.setSortSn(sortMax + 1);
            ciamsCodeMapper.insertCode(ciamsCode);
        }
    }

    @Override
    public CiamsCodeDto.Find selectCodeByCode(String code) {
        return ciamsCodeMapper.selectCodeByCode(code);
    }

    @Override
    public void modify(Modify mod) {
        CiamsCode ciamsCode = new CiamsCode();
        BeanUtils.copyProperties(mod, ciamsCode);

        ciamsCodeMapper.updateCode(ciamsCode);
    }

    @Override
    public void removeByCode(String code) throws Exception {
        List<CiamsCodeDto.Find> childCodes = ciamsCodeMapper.selectCodeListByParentCode(code);

        if (childCodes.size() > 0) {
            throw new CustomException("하위 트리 코드값이 존재합니다.");
        }

        ciamsCodeMapper.deleteByCode(code);
    }

    @Override
    @Transactional
    public void changeCodePriority(List<CiamsCode> codeList) {
        for (CiamsCode code : codeList) {
            ciamsCodeMapper.updateCode(code);
        }
    }

    @Override
    public List<CiamsCode> selectCodeSublist(CiamsCodeDto.Sub sub) {
        return ciamsCodeMapper.selectCodeSublist(sub);
    }

}
