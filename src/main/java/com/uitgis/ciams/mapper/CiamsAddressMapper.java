package com.uitgis.ciams.mapper;

import com.uitgis.ciams.dto.CiamsAddressDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiamsAddressMapper {
    /**
     * 읍면동 리스트
     */
    public List<CiamsAddressDto.Search.Result> selectAddressEmdList();

    /**
     * 리 리스트
     */
    public List<CiamsAddressDto.Search.Result> selectAddressRiList(CiamsAddressDto.Search.Params.RI params);

    /**
     * PNU 리스트 -> 주소 리스트
     */
    public CiamsAddressDto.Info selectPnuInfo(String pnu);
}
