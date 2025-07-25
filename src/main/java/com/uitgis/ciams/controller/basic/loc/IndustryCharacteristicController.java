package com.uitgis.ciams.controller.basic.loc;

import com.uitgis.ciams.dto.basic.loc.characteristic.CharResultDto;
import com.uitgis.ciams.dto.basic.loc.characteristic.ItaDto;
import com.uitgis.ciams.dto.basic.loc.characteristic.StatusDto;
import com.uitgis.ciams.service.basic.loc.characteristic.ITAService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/basic/loc/ind-char")
@RestController
public class IndustryCharacteristicController {
    private final ITAService itaService;


    /**
     * 산업기반분석
     *
     * @return
     */
    @PostMapping("/ita")
    public ResponseEntity<ItaDto.Info.Find.Result> getItaInfo() {
        ItaDto.Info.Find.Result result = itaService.getItaInfo();
        return ResponseEntity.ok(result);
    }


    /**
     * 산업기반분석 업종
     *
     * @param params
     * @return
     */
    @PostMapping("/ita/data")
    public ResponseEntity<ItaDto.Data.Search.Result> getItaData(@RequestBody ItaDto.Data.Search.Params params) {
        ItaDto.Data.Search.Result result = itaService.getItaData(params);
        return ResponseEntity.ok(result);
    }


    /**
     * 산업 현황
     *
     * @param params
     * @return
     */
    @PostMapping("/ita/status")
    public ResponseEntity<StatusDto.IndustryStatus.Find.Result> getIndustryStatus(@RequestBody StatusDto.IndustryStatus.Find.Params params) {
        StatusDto.IndustryStatus.Find.Result result = itaService.getIndustryStatus(params);
        return ResponseEntity.ok(result);
    }


    /**
     * 산업특성분석 결과
     *
     * @param params
     * @return
     */
    @GetMapping("/ita/result")
    public ResponseEntity<CharResultDto.Char.Search.Result> getItaResultDataList(CharResultDto.Char.Search.Params params) {
        CharResultDto.Char.Search.Result result = itaService.getItaResultDataList(params);
        return ResponseEntity.ok(result);
    }


    /**
     * 인접 지자체 목록
     *
     * @return
     */
    @GetMapping("/adjacent")
    public ResponseEntity<List<ItaDto.Adjacent>> getAdjacents() {
        List<ItaDto.Adjacent> result = itaService.getAdjacents(true);
        return ResponseEntity.ok(result);
    }

}
