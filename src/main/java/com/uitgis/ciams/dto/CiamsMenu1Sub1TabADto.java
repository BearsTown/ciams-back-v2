package com.uitgis.ciams.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public class CiamsMenu1Sub1TabADto {

    @Getter
    @Setter
    public static class CiamsStatus {
        private Integer id;
        private Integer parentId;
        private String title;
        private int priority;
        private List<CiamsStatus> children;
    }

    @Getter
    @Setter
    public static class DataInfo {
        private StatusData statusData;
        private List<StatusColumn> columns;
        private List<PivotColumn> pivotColumns;
        private List<Map<String, Object>> data;
        private Chart chart;
    }



    @Getter
    @Setter
    public static class StatusGroup {
        private int statusGroupId;
        private int statusId;
        private int dataId;
        private String title;
        private String chartAxis;
        private String view;
        private int priority;
    }

    @Getter
    @Setter
    public static class StatusData {
        private String dataName;
        private boolean pivot;
    }

    @Getter
    @Setter
    public static class StatusColumn {
        private String name;
        private String label;
        private boolean useCode;
        private boolean useAxis;
        private String dataType;
        private String unit;
        private String seriesType;
    }


    @Getter
    @Setter
    public static class Pivot {
        private String rColumn;
        private String cColumn;
        private String vColumn;
    }


    @Getter
    @Setter
    @Builder
    public static class PivotParams {
        private int dataId;
        private Pivot pivot;
        private String dataName;
        private List<String> columns;
    }


    @Getter
    @Setter
    public static class PivotColumn {
        private String name;
        private String label;
        private boolean useAxis;
        private String dataType;
    }

    @Getter
    @Setter
    public static class Chart {
        private List<String> legend;
        private List<String> categories;
    }
}
