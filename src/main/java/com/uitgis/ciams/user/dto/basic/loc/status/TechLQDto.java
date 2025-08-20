package com.uitgis.ciams.user.dto.basic.loc.status;

import com.uitgis.ciams.model.basic.loc.status.CiamsTechLQ;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TechLQDto {

    public static class HighTech {
        public static class Find {

            @Getter
            @Setter
            public static class Params {
                private String parentTechCd;
            }

            @Getter
            @Setter
            public static class Result {
                private String parentTechCd;
                private String parentTechNm;
                private List<Tech> techs;
            }

            @Getter
            @Setter
            public static class Tech {
                private String techCd;
                private String techNm;
                private double corpLq;
                private double workerLq;
                private boolean corpQualified;
                private boolean workerQualified;
            }
        }
    }


    public static class TechLQ {
        public static class Find {

            @Getter
            @Setter
            public static class Params {
                private String techCd;
            }

            @Getter
            @Setter
            public static class Result extends CiamsTechLQ {
                //
            }
        }
    }

}
