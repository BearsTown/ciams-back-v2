package com.uitgis.ciams.service;

import com.uitgis.ciams.dto.CiamsAddressDto;

import java.util.List;

public interface CiamsAddressService {
    /**
     * 읍면동 리스트
     */
    public List<CiamsAddressDto.Search.Result> getAddressEmdList();

    /**
     * 리 리스트
     */
    public List<CiamsAddressDto.Search.Result> getAddressRiList(CiamsAddressDto.Search.Params.RI params);

    /**
     * PNU 리스트 -> 주소 리스트
     */
    public CiamsAddressDto.Info getPnuInfo(String pnu);
}
