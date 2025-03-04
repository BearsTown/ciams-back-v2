package com.uitgis.ciams.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationDto {
    private int size = 10;  // 페이지당 row 수
    private int pageNo = 1; // 현재 페이지 번호
    private int start;      // 시작 row 번호
    private int end;        // 마지막 row 번호
    private int totalCount; // 전체 row 수
    private boolean isNumSortAsc = true;	//번호(rn) 순차여부
    private boolean isPage = true;			//페이징처리 사용여부
}
