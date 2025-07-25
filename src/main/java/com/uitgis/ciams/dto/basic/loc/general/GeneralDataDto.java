package com.uitgis.ciams.dto.basic.loc.general;

import com.uitgis.ciams.dto.CiamsSourceGroupDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public class GeneralDataDto {
    /**
     * 일반현황 데이터 Item
     */
    @Getter
    @Setter
    public static class DataItem {
        private Integer id;
        private Integer parentId;
        private String title;
        private int priority;
        private List<DataItem> children;
    }


    /**
     * 데이터 그룹 정보
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataGroupInfo {
        List<DataGroup> groups;
        List<CiamsSourceGroupDto.Find.Result> sources;
    }


    /**
     * 데이터 그룹
     */
    @Getter
    @Setter
    public static class DataGroup {
        private int statusGroupId;
        private int statusId;
        private int dataId;
        private String title;
        private String view;
        private String chartAxis;
        private int priority;
    }


    /**
     * 데이터 상세 정보
     */
    @Getter
    @Setter
    public static class DataDetailInfo {
        private ChartInfo chartInfo;
        private DataTable dataTable;
        private List<DataColumn> columns;
        private List<PivotColumn> pivotColumns;
        private List<Map<String, Object>> data;
    }


    /**
     * 데이터 테이블
     */
    @Getter
    @Setter
    public static class DataTable {
        private String dataName;
        private boolean pivot;
    }


    /**
     * 데이터 컬럼
     */
    @Getter
    @Setter
    public static class DataColumn {
        private String name;
        private String label;
        private boolean useCode;
        private boolean useAxis;
        private String dataType;
        private String unit;
        private String seriesType;
    }


    /**
     * 피벗 속성
     */
    @Getter
    @Setter
    public static class Pivot {
        private String rColumn;
        private String cColumn;
        private String vColumn;
    }


    /**
     * 피벗 매개변수
     */
    @Getter
    @Setter
    @Builder
    public static class PivotParams {
        private int dataId;
        private Pivot pivot;
        private String dataName;
        private List<String> columns;
    }


    /**
     * 피벗 컬럼
     */
    @Getter
    @Setter
    public static class PivotColumn {
        private String name;
        private String label;
        private boolean useAxis;
        private String dataType;
    }

    /**
     * 차트 정보
     */
    @Getter
    @Setter
    public static class ChartInfo {
        private List<String> legend;
        private List<String> categories;
    }

}
