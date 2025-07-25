package com.uitgis.ciams.service.impl.basic.loc.general;

import com.uitgis.ciams.dto.CiamsSourceGroupDto;
import com.uitgis.ciams.dto.basic.loc.general.GeneralDataDto;
import com.uitgis.ciams.mapper.CiamsSourceMapper;
import com.uitgis.ciams.mapper.basic.loc.general.GeneralDataMapper;
import com.uitgis.ciams.service.basic.loc.general.GeneralDataService;
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
public class GeneralDataServiceImpl implements GeneralDataService {
    private final GeneralDataMapper generalDataMapper;
    private final CiamsSourceMapper ciamsSourceMapper;

    /**
     * 일반현황 데이터 Item 목록
     *
     * @return
     */
    @Override
    public List<GeneralDataDto.DataItem> getGeneralDataItems() {
        List<GeneralDataDto.DataItem> allNodes = generalDataMapper.findAllGeneralDataItems();

        List<GeneralDataDto.DataItem> roots = allNodes.stream()
                .filter(node -> node.getParentId() == null)
                .collect(Collectors.toList());

        for (GeneralDataDto.DataItem root : roots) {
            setChildren(root, allNodes);
        }

        return roots;
    }


    /**
     * 데이터 그룹 정보
     *
     * @param itemId 데이터 Item ID
     * @return
     */
    @Override
    public GeneralDataDto.DataGroupInfo getDataGroupInfo(int itemId) {
        CiamsSourceGroupDto.Find.Params params = CiamsSourceGroupDto.Find.Params
                .builder()
                .category("일반현황")
                .targetId(Integer.toString(itemId))
                .build();

        return GeneralDataDto.DataGroupInfo
                .builder()
                .sources(ciamsSourceMapper.findAllSources(params))
                .groups(generalDataMapper.findAllDataGroupsById(itemId))
                .build();
    }


    /**
     * 데이터 상세 정보
     *
     * @param dataId 데이터 ID
     * @return
     */
    @Override
    public GeneralDataDto.DataDetailInfo getDataDetailInfo(int dataId) {
        GeneralDataDto.DataDetailInfo dataDetailInfo = new GeneralDataDto.DataDetailInfo();
        GeneralDataDto.ChartInfo chartInfo = new GeneralDataDto.ChartInfo();

        GeneralDataDto.DataTable dataTable = generalDataMapper.findDataTableById(dataId);
        List<GeneralDataDto.DataColumn> dataColumns = generalDataMapper.findAllDataColumnsById(dataId);

        if (dataTable.isPivot()) {
            GeneralDataDto.Pivot pivot = generalDataMapper.findPivotById(dataId);

            Optional<String> dataType = dataColumns.stream()
                    .filter(GeneralDataDto.DataColumn::isUseAxis)
                    .map(GeneralDataDto.DataColumn::getDataType)
                    .findFirst();

            Map<String, String> map = new HashMap<>();
            map.put("cColumn", pivot.getCColumn());
            map.put("dataType", dataType.orElse("STRING"));
            map.put("tableName", dataTable.getDataName());

            List<GeneralDataDto.PivotColumn> pivotColumns = generalDataMapper.findAllPivotByIdColumns(map);

            dataDetailInfo.setPivotColumns(pivotColumns);

            List<String> columnParams = pivotColumns.stream()
                    .filter(GeneralDataDto.PivotColumn::isUseAxis)
                    .map(GeneralDataDto.PivotColumn::getLabel)
                    .toList();

            GeneralDataDto.PivotParams params = GeneralDataDto.PivotParams
                    .builder()
                    .dataId(dataId)
                    .dataName(dataTable.getDataName())
                    .pivot(pivot)
                    .columns(columnParams)
                    .build();

            List<Map<String, Object>> data = generalDataMapper.findAllPivotRecords(params);

            List<String> legend = dataColumns.stream()
                    .filter(GeneralDataDto.DataColumn::isUseAxis)
                    .map(GeneralDataDto.DataColumn::getLabel)
                    .toList();

            chartInfo.setLegend(legend);
            chartInfo.setCategories(columnParams);

            dataDetailInfo.setData(data);
        } else {
            List<Map<String, Object>> data = generalDataMapper.findAllRecordsByName(dataTable.getDataName());

            List<String> legend = dataColumns.stream()
                    .filter(GeneralDataDto.DataColumn::isUseAxis)
                    .map(GeneralDataDto.DataColumn::getLabel)
                    .toList();

            String key = dataColumns.get(0).getName();

            List<String> categories = data.stream()
                    .filter(map -> map.containsKey(key))
                    .map(map -> String.valueOf(map.get(key)))
                    .collect(Collectors.toList());

            chartInfo.setLegend(legend);
            chartInfo.setCategories(categories);

            dataDetailInfo.setData(data);
        }

        dataDetailInfo.setColumns(dataColumns);
        dataDetailInfo.setDataTable(dataTable);
        dataDetailInfo.setChartInfo(chartInfo);

        return dataDetailInfo;
    }


    private void setChildren(GeneralDataDto.DataItem parent, List<GeneralDataDto.DataItem> allNodes) {
        List<GeneralDataDto.DataItem> children = allNodes.stream()
                .filter(node -> parent.getId().equals(node.getParentId()))
                .collect(Collectors.toList());

        parent.setChildren(children);

        for (GeneralDataDto.DataItem child : children) {
            setChildren(child, allNodes);
        }
    }

}
