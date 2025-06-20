package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.CiamsMenu1Sub1TabADto;
import com.uitgis.ciams.mapper.CiamsStatusDataMapper;
import com.uitgis.ciams.service.CiamsMenu1Sub1TabAService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsMenu1Sub1TabAServiceImpl implements CiamsMenu1Sub1TabAService {
    private final CiamsStatusDataMapper ciamsStatusDataMapper;

    @Override
    public List<CiamsMenu1Sub1TabADto.CiamsStatus> getStatusTree() {
        List<CiamsMenu1Sub1TabADto.CiamsStatus> allNodes = ciamsStatusDataMapper.selectStatusTree();

        List<CiamsMenu1Sub1TabADto.CiamsStatus> roots = allNodes.stream()
                .filter(node -> node.getParentId() == null)
                .collect(Collectors.toList());

        for (CiamsMenu1Sub1TabADto.CiamsStatus root : roots) {
            setChildren(root, allNodes);
        }

        return roots;
    }

    @Override
    public List<CiamsMenu1Sub1TabADto.StatusGroup> getStatusGroups(int statusId) {
        return ciamsStatusDataMapper.selectStatusGroups(statusId);
    }

    @Override
    public CiamsMenu1Sub1TabADto.DataInfo getDataInfo(int dataId) {
        CiamsMenu1Sub1TabADto.DataInfo dataInfo = new CiamsMenu1Sub1TabADto.DataInfo();
        CiamsMenu1Sub1TabADto.StatusData statusData = ciamsStatusDataMapper.selectStatusData(dataId);
        List<CiamsMenu1Sub1TabADto.StatusColumn> statusColumns = ciamsStatusDataMapper.selectStatusColumns(dataId);
        CiamsMenu1Sub1TabADto.Chart chart = new CiamsMenu1Sub1TabADto.Chart();

        if (statusData.isPivot()) {
            CiamsMenu1Sub1TabADto.Pivot pivot = ciamsStatusDataMapper.selectPivot(dataId);

            Optional<String> dataType = statusColumns.stream()
                    .filter(CiamsMenu1Sub1TabADto.StatusColumn::isUseAxis)
                    .map(CiamsMenu1Sub1TabADto.StatusColumn::getDataType)
                    .findFirst();

            Map<String, String> map = new HashMap<>();
            map.put("cColumn", pivot.getCColumn());
            map.put("dataType", dataType.orElse("STRING"));
            map.put("tableName", statusData.getDataName());

            List<CiamsMenu1Sub1TabADto.PivotColumn> pivotColumns = ciamsStatusDataMapper.selectStatusPivotColumns(map);

            dataInfo.setPivotColumns(pivotColumns);



            List<String> columnParams = pivotColumns.stream()
                    .filter(CiamsMenu1Sub1TabADto.PivotColumn::isUseAxis)
                    .map(CiamsMenu1Sub1TabADto.PivotColumn::getLabel)
                    .toList();

            CiamsMenu1Sub1TabADto.PivotParams params = CiamsMenu1Sub1TabADto.PivotParams
                    .builder()
                    .dataId(dataId)
                    .dataName(statusData.getDataName())
                    .pivot(pivot)
                    .columns(columnParams)
                    .build();

            List<Map<String, Object>> data  = ciamsStatusDataMapper.findAllPivotRecords(params);
//            List<String> categories = data.stream()
//                    .filter(m -> m.containsKey("A0"))
//                    .map(m -> String.valueOf(m.get("A0")))
//                    .collect(Collectors.toList());

//            List<String> categories = columnParams;

            List<String> legend = statusColumns.stream()
                    .filter(CiamsMenu1Sub1TabADto.StatusColumn::isUseAxis)
                    .map(CiamsMenu1Sub1TabADto.StatusColumn::getLabel)
                    .toList();

            chart.setLegend(legend);
            chart.setCategories(columnParams);

            dataInfo.setData(data);
        } else {
            List<Map<String, Object>> data = ciamsStatusDataMapper.findAllRecords(statusData.getDataName());

            List<String> legend = statusColumns.stream()
                    .filter(CiamsMenu1Sub1TabADto.StatusColumn::isUseAxis)
                    .map(CiamsMenu1Sub1TabADto.StatusColumn::getLabel)
                    .toList();

            String key = statusColumns.get(0).getName();

            List<String> categories = data.stream()
                    .filter(map -> map.containsKey(key))
                    .map(map -> String.valueOf(map.get(key)))
                    .collect(Collectors.toList());

            chart.setLegend(legend);
            chart.setCategories(categories);

            dataInfo.setData(data);
        }

        dataInfo.setChart(chart);
        dataInfo.setColumns(statusColumns);
        dataInfo.setStatusData(statusData);

        return dataInfo;
    }

    private void setChildren(CiamsMenu1Sub1TabADto.CiamsStatus parent, List<CiamsMenu1Sub1TabADto.CiamsStatus> allNodes) {
        List<CiamsMenu1Sub1TabADto.CiamsStatus> children = allNodes.stream()
                .filter(node -> parent.getId().equals(node.getParentId()))
                .collect(Collectors.toList());

        parent.setChildren(children);

        for (CiamsMenu1Sub1TabADto.CiamsStatus child : children) {
            setChildren(child, allNodes);
        }
    }
}
