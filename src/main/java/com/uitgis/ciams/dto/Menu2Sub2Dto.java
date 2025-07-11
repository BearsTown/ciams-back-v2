package com.uitgis.ciams.dto;

import com.uitgis.ciams.model.CiamsData;
import com.uitgis.ciams.model.CiamsDataAttribute;
import com.uitgis.ciams.model.CiamsDataColumn;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Menu2Sub2Dto {

    @Getter
    @Setter
    public static class DataConfig {
        private List<CiamsData> years;
        private List<CiamsDataColumn> columns;
        private List<CiamsDataAttribute> attributes;
    }

//    public static class DataConfig {
//        public static class Find {
//            @Getter
//            @Setter
//            public static class Params {
//
//            }
//
//            @Getter
//            @Setter
//            public static class Result extends CiamsDataGroup {
//
//            }
//        }
//    }
}
