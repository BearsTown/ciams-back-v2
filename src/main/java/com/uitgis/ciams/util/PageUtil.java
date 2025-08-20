package com.uitgis.ciams.util;

import com.uitgis.ciams.user.dto.PaginationDto;

public class PageUtil {
    public static PaginationDto setTotalCount(PaginationDto pageDto, int total) {
        int size = pageDto.getSize();
        int pageNo = pageDto.getPageNo();

        if (size > 0 && total > 0 && pageNo > 0) {
            int start = (pageNo - 1) * size + 1;
            int end = start + size - 1;

            if (end > total) {
                end = total;
            }

            pageDto.setStart(start);
            pageDto.setEnd(end);
        }
        pageDto.setTotalCount(total);

        return pageDto;
    }
}
