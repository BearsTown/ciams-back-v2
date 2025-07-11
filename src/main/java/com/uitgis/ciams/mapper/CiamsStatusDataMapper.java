package com.uitgis.ciams.mapper;

import com.uitgis.ciams.dto.Menu1Sub1TabADto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CiamsStatusDataMapper {
    public List<Menu1Sub1TabADto.CiamsStatus> selectStatusTree();

    public List<Menu1Sub1TabADto.StatusGroup> selectStatusGroups(int statusId);

    public Menu1Sub1TabADto.StatusData selectStatusData(int dataId);

    public List<Menu1Sub1TabADto.StatusColumn> selectStatusColumns(int dataId);

    public List<Map<String, Object>> findAllRecords(String dataName);

    public List<Map<String, Object>> findAllPivotRecords(Menu1Sub1TabADto.PivotParams params);


    public Menu1Sub1TabADto.Pivot selectPivot(int dataId);

    public List<Menu1Sub1TabADto.PivotColumn> selectStatusPivotColumns(Map<String, String> map);

}
