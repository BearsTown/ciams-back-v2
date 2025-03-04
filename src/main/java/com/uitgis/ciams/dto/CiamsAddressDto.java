package com.uitgis.ciams.dto;

import com.uitgis.ciams.model.CiamsAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CiamsAddressDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Info {
        private String pnu;
        private String address1;
        private String address2;
        private String address3;
        private Attribute attribute;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Attribute extends CiamsAddress {
        private String sidoCd;
        private String sidoNm;
        private String bonbun;
        private String bubun;
        private String parcelType;
        private String parcelName;
    }

    public static class Search {

        public static class Params {
            @Getter
            @Setter
            public static class RI {
                private String emdCd;
            }
        }

        @Getter
        @Setter
        @Builder
        public static class Result {
            private String codeName;
            private String codeValue;
        }
    }

}
