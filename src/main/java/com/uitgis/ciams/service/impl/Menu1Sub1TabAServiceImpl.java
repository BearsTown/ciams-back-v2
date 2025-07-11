package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.Menu1Sub1TabADto;
import com.uitgis.ciams.dto.CiamsSourceGroupDto;
import com.uitgis.ciams.mapper.CiamsSourceMapper;
import com.uitgis.ciams.mapper.CiamsStatusDataMapper;
import com.uitgis.ciams.service.Menu1Sub1TabAService;
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
public class Menu1Sub1TabAServiceImpl implements Menu1Sub1TabAService {
    private final CiamsStatusDataMapper ciamsStatusDataMapper;
    private final CiamsSourceMapper ciamsSourceMapper;

    @Override
    public List<Menu1Sub1TabADto.CiamsStatus> getStatusTree() {
        List<Menu1Sub1TabADto.CiamsStatus> allNodes = ciamsStatusDataMapper.selectStatusTree();

        List<Menu1Sub1TabADto.CiamsStatus> roots = allNodes.stream()
                .filter(node -> node.getParentId() == null)
                .collect(Collectors.toList());

        for (Menu1Sub1TabADto.CiamsStatus root : roots) {
            setChildren(root, allNodes);
        }

        return roots;
    }

//    @Override
//    public List<CiamsMenu1Sub1TabADto.StatusGroup> getStatusGroups(int statusId) {
//        return ciamsStatusDataMapper.selectStatusGroups(statusId);
//    }

    @Override
    public Menu1Sub1TabADto.CiamsStatusInfo getStatusInfo(int statusId) {
        CiamsSourceGroupDto.Find.Params params = CiamsSourceGroupDto.Find.Params
                .builder()
                .category("일반현황")
                .targetId(Integer.toString(statusId))
                .build();

        return Menu1Sub1TabADto.CiamsStatusInfo
                .builder()
                .sources(ciamsSourceMapper.selectSources(params))
                .groups(ciamsStatusDataMapper.selectStatusGroups(statusId))
                .build();
    }


    @Override
    public Menu1Sub1TabADto.DataInfo getDataInfo(int dataId) {
        Menu1Sub1TabADto.DataInfo dataInfo = new Menu1Sub1TabADto.DataInfo();
        Menu1Sub1TabADto.StatusData statusData = ciamsStatusDataMapper.selectStatusData(dataId);
        List<Menu1Sub1TabADto.StatusColumn> statusColumns = ciamsStatusDataMapper.selectStatusColumns(dataId);
        Menu1Sub1TabADto.Chart chart = new Menu1Sub1TabADto.Chart();

        if (statusData.isPivot()) {
            Menu1Sub1TabADto.Pivot pivot = ciamsStatusDataMapper.selectPivot(dataId);

            Optional<String> dataType = statusColumns.stream()
                    .filter(Menu1Sub1TabADto.StatusColumn::isUseAxis)
                    .map(Menu1Sub1TabADto.StatusColumn::getDataType)
                    .findFirst();

            Map<String, String> map = new HashMap<>();
            map.put("cColumn", pivot.getCColumn());
            map.put("dataType", dataType.orElse("STRING"));
            map.put("tableName", statusData.getDataName());

            List<Menu1Sub1TabADto.PivotColumn> pivotColumns = ciamsStatusDataMapper.selectStatusPivotColumns(map);

            dataInfo.setPivotColumns(pivotColumns);



            List<String> columnParams = pivotColumns.stream()
                    .filter(Menu1Sub1TabADto.PivotColumn::isUseAxis)
                    .map(Menu1Sub1TabADto.PivotColumn::getLabel)
                    .toList();

            Menu1Sub1TabADto.PivotParams params = Menu1Sub1TabADto.PivotParams
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
                    .filter(Menu1Sub1TabADto.StatusColumn::isUseAxis)
                    .map(Menu1Sub1TabADto.StatusColumn::getLabel)
                    .toList();

            chart.setLegend(legend);
            chart.setCategories(columnParams);

            dataInfo.setData(data);
        } else {
            List<Map<String, Object>> data = ciamsStatusDataMapper.findAllRecords(statusData.getDataName());

            List<String> legend = statusColumns.stream()
                    .filter(Menu1Sub1TabADto.StatusColumn::isUseAxis)
                    .map(Menu1Sub1TabADto.StatusColumn::getLabel)
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

    private void setChildren(Menu1Sub1TabADto.CiamsStatus parent, List<Menu1Sub1TabADto.CiamsStatus> allNodes) {
        List<Menu1Sub1TabADto.CiamsStatus> children = allNodes.stream()
                .filter(node -> parent.getId().equals(node.getParentId()))
                .collect(Collectors.toList());

        parent.setChildren(children);

        for (Menu1Sub1TabADto.CiamsStatus child : children) {
            setChildren(child, allNodes);
        }
    }
}
