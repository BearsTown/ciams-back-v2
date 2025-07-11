package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.Menu2Sub2Dto;
import com.uitgis.ciams.mapper.CiamsDataAttributeMapper;
import com.uitgis.ciams.mapper.CiamsDataColumnMapper;
import com.uitgis.ciams.mapper.CiamsDataGroupMapper;
import com.uitgis.ciams.mapper.CiamsDataMapper;
import com.uitgis.ciams.model.CiamsDataGroup;
import com.uitgis.ciams.service.Menu1Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class Menu1ServiceImpl implements Menu1Service {
    private final CiamsDataMapper ciamsDataMapper;
    private final CiamsDataColumnMapper ciamsDataColumnMapper;
    private final CiamsDataGroupMapper ciamsDataGroupMapper;
    private final CiamsDataAttributeMapper ciamsDataAttributeMapper;


    @Override
    public List<CiamsDataGroup> getDataGroups(int parentId) {
        return ciamsDataGroupMapper.selectDataGroups(parentId);
    }

    @Override
    public Menu2Sub2Dto.DataConfig getDataConfig(int dataGroupId) {
        Menu2Sub2Dto.DataConfig  config = new Menu2Sub2Dto.DataConfig();
        config.setYears(ciamsDataMapper.selectDatas(dataGroupId));
        config.setColumns(ciamsDataColumnMapper.selectDataColumns(dataGroupId));
        config.setAttributes(ciamsDataAttributeMapper.selectDataAttributes(dataGroupId));
        return config;
    }
}
