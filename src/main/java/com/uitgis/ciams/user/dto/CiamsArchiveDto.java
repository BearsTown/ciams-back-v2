package com.uitgis.ciams.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uitgis.ciams.model.CiamsArchive;
import com.uitgis.ciams.model.CiamsFile;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CiamsArchiveDto {

    @Getter
    @Setter
    public static class Find extends PaginationDto {
        private String categoryId;
        private String typeCode;
        private String title;
        private Boolean hidden;
    }

    @Getter
    @Setter
    public static class WithFile extends CiamsArchive {
        //		private MultipartFile imgFile;				//대표이미지
        List<CiamsFile> archiveFiles;
    }

    @Getter
    @Setter
    public static class ListResult extends CiamsArchive {
        @JsonIgnore
        public String getContents() {
            return super.getContents();
        }

        private List<CiamsFile> archiveFiles;
    }
}
