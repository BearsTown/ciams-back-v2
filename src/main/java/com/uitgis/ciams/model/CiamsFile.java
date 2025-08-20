package com.uitgis.ciams.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CiamsFile {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String createUser;  // '생성자 ID';

    private String id;          // 'ID'
    private String linkId;      // '연결 ID';
    private String orgName;     // '실제 파일명';
    private String newName;     // '저장된 파일명';
    private String path;        // '파일 저장 경로 ';
    private String ext;         // '파일 확장자';
    private Long size;          // '파일 사이즈';
    private String typeCode;    // '구분 코드';
    private String status;    // '상태';
    private Integer sortSn;	// '파일 순서'
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;  // '생성일';
//    private String createDate;  // '생성일';
}
