package com.uitgis.gis.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class UploadDataDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Upload {
        @Builder
        public Upload(MultipartFile file, String encoding) {
            this.file = file;
            this.encoding = encoding;
        }

        @NotNull
        private MultipartFile file;
        private String encoding = "UTF-8";
        private String crs;     // 좌표계
        // 타원체 변환이 필요할 경우 설정하며,true 또는 forward 는 Bessel 에서 WGS84(GRS80)으로
        // reverse는 WGS84(GRS80)에서 Bessel 타원체로의 변환 파라메터가 자동 추가되 타원체 변환을 수행함..
        private String badekas; // 세계 측지계 변환 여부
    }

}
