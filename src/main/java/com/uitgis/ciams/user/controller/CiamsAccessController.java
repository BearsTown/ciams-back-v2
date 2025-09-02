package com.uitgis.ciams.user.controller;

import com.uitgis.ciams.user.dto.CiamsAccessDto;
import com.uitgis.ciams.user.service.CiamsAccessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/access")
public class CiamsAccessController {

    private final CiamsAccessService ciamsAccessService;


    /**
     * 메뉴 접근 목록 조회.
     *
     * @return 하위 코드 목록
     * @throws Exception
     */
    @GetMapping("/menu/check")
    public ResponseEntity<?> menuAcccessList() throws Exception {
		List<CiamsAccessDto.MenuAccess> result = ciamsAccessService.getMenuAccessList();

		return ResponseEntity.ok(result);
    }
}
