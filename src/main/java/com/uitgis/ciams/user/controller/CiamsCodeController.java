package com.uitgis.ciams.user.controller;

import com.uitgis.ciams.admin.dto.CodeDto;
import com.uitgis.ciams.model.CiamsCode;
import com.uitgis.ciams.user.dto.CiamsCodeDto;
import com.uitgis.ciams.user.service.CiamsCodeService;
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
@RestController
@RequestMapping("/api/v1/codes")
public class CiamsCodeController {
    private final CiamsCodeService ciamsCodeService;


    @GetMapping("/{code}")
    public ResponseEntity<?> getCode(@PathVariable String code) {
        CiamsCodeDto.Find result = ciamsCodeService.selectCodeByCode(code);
        return ResponseEntity.ok(result);
    }


    /**
     * 하위 코드 목록 조회.
     *
     * @param parentCode 상위 코드 아이디
     * @return 하위 코드 목록
     */
    @GetMapping("/{parentCode}/child")
    public ResponseEntity<?> list(@PathVariable String parentCode) {
        List<CiamsCodeDto.Find> result = null;
        //root코드 하위 목록
        if (parentCode.equals("0")) {
            result = ciamsCodeService.selectCodeListByParentCode(null);
        } else {
            result = ciamsCodeService.selectCodeListByParentCode(parentCode);
        }

        return ResponseEntity.ok(result);
    }


    @GetMapping("/sub")
    public ResponseEntity<?> selectCodeSublist(CiamsCodeDto.Sub sub) {
        List<CiamsCode> list = ciamsCodeService.selectCodeSublist(sub);
        return ResponseEntity.ok(list);
    }

}
