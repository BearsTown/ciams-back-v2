package com.uitgis.ciams.controller;

import com.uitgis.ciams.dto.CiamsAddressDto;
import com.uitgis.ciams.service.CiamsAddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/address")
@RestController
@RequiredArgsConstructor
public class CiamsAddressController {
    private final CiamsAddressService ciamsAddressService;

    /**
     * 읍면동 리스트
     */
    @GetMapping("/emd/list")
    public ResponseEntity<List<CiamsAddressDto.Search.Result>> getAddressEmdList() {
        List<CiamsAddressDto.Search.Result> result = ciamsAddressService.getAddressEmdList();
        return ResponseEntity.ok(result);
    }


    /**
     * 리 리스트
     */
    @GetMapping("/ri/list")
    public ResponseEntity<List<CiamsAddressDto.Search.Result>> getAddressRiList(CiamsAddressDto.Search.Params.RI params) {
        List<CiamsAddressDto.Search.Result> result = ciamsAddressService.getAddressRiList(params);
        return ResponseEntity.ok(result);
    }

    /**
     * PNU -> 주소 정보
     */
    @GetMapping("/info/{pnu}")
    public ResponseEntity<CiamsAddressDto.Info> getPnuInfo(@PathVariable String pnu) {
        CiamsAddressDto.Info result = ciamsAddressService.getPnuInfo(pnu);
        return ResponseEntity.ok(result);
    }
}
