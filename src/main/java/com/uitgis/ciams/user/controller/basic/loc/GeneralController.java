package com.uitgis.ciams.user.controller.basic.loc;

import com.uitgis.ciams.user.dto.basic.loc.general.GeneralDataDto;
import com.uitgis.ciams.user.service.basic.loc.general.GeneralDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/basic/loc/general")
@RestController
public class GeneralController {
    private final GeneralDataService generalDataService;


    /**
     * 일반현황 데이터 Item 목록
     */
    @GetMapping("/data/items")
    public ResponseEntity<List<GeneralDataDto.DataItem>> getGeneralDataItems() {
        List<GeneralDataDto.DataItem> result = generalDataService.getGeneralDataItems();
        return ResponseEntity.ok(result);
    }


    /**
     * 데이터 그룹 정보
     *
     * @param itemId 데이터 Item ID
     * @return
     */
    @GetMapping("/data/{itemId}/group")
    public ResponseEntity<GeneralDataDto.DataGroupInfo> getDataGroupInfo(@PathVariable("itemId") int itemId) {
        GeneralDataDto.DataGroupInfo result = generalDataService.getDataGroupInfo(itemId);
        return ResponseEntity.ok(result);
    }


    /**
     * 데이터 상세 정보
     *
     * @param dataId 데이터 ID
     * @return
     */
    @GetMapping("/data/{dataId}/info")
    public ResponseEntity<GeneralDataDto.DataDetailInfo> getDataDetailInfo(@PathVariable("dataId") int dataId) {
        GeneralDataDto.DataDetailInfo result = generalDataService.getDataDetailInfo(dataId);
        return ResponseEntity.ok(result);
    }

}
