package com.uitgis.ciams.service.impl;

import com.uitgis.ciams.dto.CiamsAddressDto;
import com.uitgis.ciams.mapper.CiamsAddressMapper;
import com.uitgis.ciams.service.CiamsAddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsAddressServiceImpl implements CiamsAddressService {
    private final CiamsAddressMapper ciamsAddressMapper;


    @Override
    public List<CiamsAddressDto.Search.Result> getAddressEmdList() {
        return ciamsAddressMapper.selectAddressEmdList();
    }

    @Override
    public List<CiamsAddressDto.Search.Result> getAddressRiList(CiamsAddressDto.Search.Params.RI params) {
        return ciamsAddressMapper.selectAddressRiList(params);
    }

    @Override
    public CiamsAddressDto.Info getPnuInfo(String pnu) {
        return ciamsAddressMapper.selectPnuInfo(pnu);
    }
}
