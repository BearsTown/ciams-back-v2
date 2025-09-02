package com.uitgis.ciams.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CiamsUser {

    private String groupCode;
    private String parentGroupCode;
    private String groupName;
    private String isteam;
    private String fullPath;
    private String fullName;
    private String userId;
    private String loginId;
    private String sortorder;
    private String userName;
    private String ssn;
    private String clssNm;
    private String positNm;
    private String mobilephone;
    private String officephone;
    private String email;
    private String description;
    private String oukid;
    private String regularity;
    private String userPassword;
    private int loginFailCnt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    private Boolean lock;
}
