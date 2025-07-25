package com.uitgis.ciams.controller;


import com.uitgis.openapi.kgeo.KGeoService;
import com.uitgis.openapi.kgeo.KGeoType;
import com.uitgis.openapi.kgeo.dto.AbstractApiResponse;
import com.uitgis.openapi.kgeo.dto.IApiRequest;
import com.uitgis.openapi.kgeo.dto.IApiResponse;
import com.uitgis.openapi.kgeo.dto.IApiResult;
import com.uitgis.openapi.kgeo.dto.services.SV0000000136;
import com.uitgis.openapi.kgeo.dto.services.SV0000000155;
import com.uitgis.openapi.kgeo.dto.services.SV0000000167;
import com.uitgis.openapi.kgeo.util.KGeoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/openapi/kgeo/")
@RestController
public class KgeoController {
    private final KGeoService kgeoService = new KGeoService();


    @PostMapping("{service}")
    public ResponseEntity<?> getKgeoServiceData(@PathVariable String service, @RequestBody Map<String, Object> params) {
        AbstractApiResponse<IApiResult> response = null;
        try {
            CompletableFuture<IApiResponse> cf = getKgeoData(KGeoType.valueOf(service), params);
            response = (AbstractApiResponse<IApiResult>) cf.get();
        } catch (Exception e) {
            ResponseEntity.ok(false);
        }

        return ResponseEntity.ok(response);
    }


    @GetMapping("{pnu}")
    public ResponseEntity<?> getKgeoUnionData(@PathVariable String pnu) {
        IApiRequest SV0000000136req = SV0000000136.Request.builder()
                .pnu(pnu)
                .build();

        IApiRequest SV0000000155req = SV0000000155.Request.builder()
                .pnu(pnu)
                .strYear(Year.now().minusYears(4).format(DateTimeFormatter.ofPattern("yyyy")))
                .endYear(Year.now().format(DateTimeFormatter.ofPattern("yyyy")))
                .build();

        IApiRequest SV0000000167req = SV0000000167.Request.builder()
                .pnu(pnu)
                .build();

        CompletableFuture<IApiResponse> SV0000000136cf = getKgeoData(KGeoType.SV0000000136, SV0000000136req);
        CompletableFuture<IApiResponse> SV0000000155cf = getKgeoData(KGeoType.SV0000000155, SV0000000155req);
//        CompletableFuture<IApiResponse> SV0000000167cf = getKgeoData(KGeoType.SV0000000167, SV0000000167req);

        log.info("K-Geo Api call. PNU :: {}", pnu);

        CompletableFuture.allOf(SV0000000136cf, SV0000000155cf).join();
//        CompletableFuture.allOf(SV0000000136cf, SV0000000155cf, SV0000000167cf).join();

        Map<String, IApiResult> result = new HashMap<>();
        result.put(KGeoType.SV0000000136.name(), null);
        result.put(KGeoType.SV0000000155.name(), null);
//        result.put(KGeoType.SV0000000167.name(), null);

        try {
            put(KGeoType.SV0000000136, SV0000000136cf, result);
            put(KGeoType.SV0000000155, SV0000000155cf, result);
//            put(KGeoType.SV0000000167, SV0000000167cf, result);
        } catch (ExecutionException | InterruptedException e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.ok(result);
    }


    private CompletableFuture<IApiResponse> getKgeoData(KGeoType kGeoType, Map<String, Object> request) throws Exception {
        return getKgeoData(kGeoType, KGeoUtil.convertRequestMapToDto(kGeoType, request));
    }


    private CompletableFuture<IApiResponse> getKgeoData(KGeoType kGeoType, IApiRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return kgeoService.executeApiCall(kGeoType, request);
            } catch (Exception e) {
                log.error("K-Geo service [{}] call error :: ", kGeoType.name() + e);
                return new AbstractApiResponse<IApiResult>() {
                };
            }
        });
    }


    private void put(KGeoType kGeoType, CompletableFuture<IApiResponse> cf, Map<String, IApiResult> result) throws ExecutionException, InterruptedException {
        AbstractApiResponse<IApiResult> response = (AbstractApiResponse<IApiResult>) cf.get();

        if (response.getCode() == 200 && response.getResult() != null) {
            result.put(kGeoType.name(), response.getResult());
        }
    }

}
