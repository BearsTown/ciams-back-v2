package com.uitgis.ciams.dto;

import com.uitgis.ciams.model.CiamsCode;
import com.uitgis.ciams.model.CiamsIncentive;
import com.uitgis.ciams.model.CiamsIncentiveCalc;
import com.uitgis.ciams.model.CiamsIncentiveLoc;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CiamsIncentiveDetailsDto {

    public static class Overview {
        public static class Find {
            @Getter
            @Setter
            public static class Params {
                private String incentiveId;
            }

            @Getter
            @Setter
            public static class Result extends CiamsIncentive {
                private String incentiveLocId;
                private String pnu;
                private String loc;
                private String jimok;
                private List<CiamsCode> areaUseCds;
            }
        }
    }


    public static class AreaSubject {
        public static class Find {
            @Getter
            @Setter
            public static class Params {
                private String incentiveId;
            }

            @Getter
            @Setter
            public static class Result extends CiamsIncentiveLoc {
                // - To DO : 기타 정보 추가
            }
        }
    }


    public static class IncentiveInfo {
        public static class Find {
            @Getter
            @Setter
            public static class Params {
                private String areaUseCd;
                private String incentiveId;
            }

            @Getter
            @Setter
            public static class Result extends CiamsIncentiveCalc {
                private String areaUseCd;
                private String areaUseNm;
                private String areaUseCodeName;
            }
        }
    }

    public static class IncentiveFile {
        @Getter
        @Setter
        public static class Insert {
            private String incentiveId;
            private List<MultipartFile> files;
        }
    }

    @Getter
    @Setter
    public static class chartGroupData {
    	String areaUseNm;
    	String jimok;
    	String year;
    	int cnt;
    	List<chartGeometryData> chartGeometryData;
    }

    @Getter
    @Setter
    public static class chartGeometryData {
    	String xy;
    	String pnu;
    }
}
