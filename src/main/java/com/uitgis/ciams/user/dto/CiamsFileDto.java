package com.uitgis.ciams.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uitgis.ciams.model.CiamsFile;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CiamsFileDto {
    /**
     * 임시 파일
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TempFile {
        private Long size;          // 사이즈
        private String ext;         // 확장자
        private String orgName;     // 실제 파일명
        private String newName;     // 저장된 파일명
        private String typeCode;    // '구분 코드';
    }

    @Getter
    @Setter
    public static class Insert extends CiamsFile {

    }

    @Getter
    @Setter
    public static class Update extends CiamsFile {

    }

    public static class Delete {
        @Getter
        @Setter
        public static class Params {
            private List<Integer> fileIds = new ArrayList<>();
        }
    }

    public static class Search {
        @Getter
        @Setter
        public static class Params extends PaginationDto {
            private String linkId;     // 연결 ID
            private String keyword;     // 검색어
            private String typeCode;    // 파일 타입
        }

        @Getter
        @Setter
        @JsonIgnoreProperties({"createUser", "newName", "path"})
        public static class Row extends CiamsFile {
            private int num; // 번호
        }

        @Getter
        @Setter
        @Builder
        public static class Result {
            private PaginationDto page; // 페이징 정보

            @Builder.Default
            private List<Row> list = new ArrayList<>();     // 검색 결과 리스트
        }
    }


    public static class EditorImage {
        @Getter
        @Setter
        public static class Upload {
            private List<MultipartFile> files = new ArrayList<>();
            private String typeCode;
            private String linkId;
        }
    }
}
