package com.uitgis.ciams.controller;


import com.uitgis.ciams.config.OpenApiConfig;
import com.uitgis.ciams.dto.CiamsKrasRequestDto;
import com.uitgis.ciams.dto.KrasResultDto;
import com.uitgis.ext.kras.KrasException;
import com.uitgis.ext.kras.KrasService;
import com.uitgis.ext.kras.dto.KrasConfig;
import com.uitgis.ext.kras.dto.xml.Kras000002Result;
import com.uitgis.ext.kras.dto.xml.Kras000026Result;
import com.uitgis.ext.kras.dto.xml.KrasResultHeader;
import com.uitgis.ext.kras.enums.KrasSvcEnum;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.security.Principal;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/openapi/kras/")
@RestController
public class KrasController {
	private final KrasService krasService = new KrasService();

	/**
	 * KRAS 연계 데이터 조회.
	 *
	 * @param krasSvcId 서비스 ID
	 */
	@GetMapping("{krasSvcId}")
	public ResponseEntity<?> unit(@PathVariable KrasSvcEnum krasSvcId, @ModelAttribute CiamsKrasRequestDto krasReq)
			throws JAXBException, XMLStreamException, KrasException, IOException {

		KrasConfig.DataParam param = OpenApiConfig.getKrasParam();
		param.setKrasSvcId(krasSvcId);
		param.setPnu(krasReq.getPnu());

		Object resultData = krasService.getKrasData(param);
		KrasResultDto resultDto = KrasResultDto.builder().resultData(resultData).build();

		return ResponseEntity.ok(resultDto);
	}

	@GetMapping
	public ResponseEntity<?> all(@ModelAttribute CiamsKrasRequestDto krasReq, Principal principal) throws Exception {
		CompletableFuture<Object> KRAS000002 = getKrasDataBySvcId(krasReq, KrasSvcEnum.KRAS000002);
		CompletableFuture<Object> KRAS000026 = getKrasDataBySvcId(krasReq, KrasSvcEnum.KRAS000026);

		log.info("KRAS Api call. username:{} pnu:{}", principal.getName(), krasReq.getPnu());

		CompletableFuture.allOf(KRAS000002, KRAS000026).join();

		Kras000002Result result1 = (Kras000002Result) KRAS000002.get();
		Kras000026Result result2 = (Kras000026Result) KRAS000026.get();

		KrasResultDto.ifAll result = new KrasResultDto.ifAll();
		if (isSuccess(result1.getHeader())) {
			result.setKrasLandInfo(result1.getBody().getLandInfo());
		}
		if (isSuccess(result2.getHeader())) {
			result.setKrasLandUsePlanBase(result2.getBody().getResultSet().getKrasLandUsePlanBase());
			result.setLandUsePlanRestrict(result2.getBody().getResultSet().getLandUsePlanRestrict());
		}

		return ResponseEntity.ok(result);
	}

	private CompletableFuture<Object> getKrasDataBySvcId(@ModelAttribute CiamsKrasRequestDto krasReq, KrasSvcEnum svcId) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				KrasConfig.DataParam param = OpenApiConfig.getKrasParam();
				param.setPnu(krasReq.getPnu());
				param.setKrasSvcId(svcId);
				return krasService.getKrasData(param);
			} catch (XMLStreamException | KrasException | IOException | JAXBException e) {
				log.error("KRAS service [{}] call error.", svcId.name() + e);
			}
			return null;
		});
	}

	private boolean isSuccess(KrasResultHeader header) {
		if (header.getCode().equals("0000")) {
			return true;
		} else {
			log.error("KRAS CALL Error.  CODE : {}, MESSAGE : {}", header.getCode(), header.getMessage());
			return false;
		}
	}
}
