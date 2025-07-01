package com.uitgis.ciams.service.impl;

import com.uitgis.spatial.adpater.mapstudio.dto.MapStudioResult;
import com.uitgis.spatial.util.ValidUtil;
import com.uitgis.gis.dto.UploadDataDto;
import com.uitgis.ciams.config.MapStudioProp;
import com.uitgis.ciams.service.CiamsGisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class CiamsGisServiceImpl implements CiamsGisService {
    private final MapStudioProp mapStudioProp;
    private final RestTemplate restTemplate;

    @Override
    public MapStudioResult.LayerBody deleteLayer(String layerName) throws Exception {
        String url = mapStudioProp.getUrl() + mapStudioProp.getApiData() + "/" + layerName;
        log.debug("Call spatial server {}", url);
        return callLayerDelete(url);
    }

    @Override
    public MapStudioResult.LayerBody uploadKrasLayer(UploadDataDto.Upload param) throws Exception {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("encoding", param.getEncoding());
        body.add("dataType", "FeatureTable");
        body.add("file", param.getFile().getResource());
        body.add("crs", param.getCrs());
        body.add("targetCRSName", param.getCrs());

        if (param.getBadekas() != null) {
            body.add("badekas", param.getBadekas());
        }

        String url = mapStudioProp.getUrl() + mapStudioProp.getApiData();
        log.debug("Call spatial server {}", url);

        return callLayerUpload(url, body);
    }


    private MapStudioResult.LayerBody callLayerDelete(String url) throws Exception {
        String token = mapStudioProp.getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (ValidUtil.notEmpty(token)) {
            headers.setBearerAuth(token);
        }
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<MapStudioResult.LayerBody> resultBody = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, MapStudioResult.LayerBody.class);

        if (HttpStatus.OK != resultBody.getStatusCode()) {
            Integer statusCode = resultBody.getStatusCode().value();
            throw new Exception("Spatial server call exception :: " + statusCode);
        }

        return resultBody.getBody();
    }

    private MapStudioResult.LayerBody callLayerUpload(String url, MultiValueMap<String, Object> body) throws Exception {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(60 * 1000);
//        factory.setReadTimeout(60 * 1000);

        restTemplate.setRequestFactory(factory);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = getRequestEntity(body, MediaType.MULTIPART_FORM_DATA);

        ResponseEntity<MapStudioResult.LayerBody> result = restTemplate.postForEntity(url, requestEntity, MapStudioResult.LayerBody.class);

        MapStudioResult.LayerBody resultBody = Objects.requireNonNull(result.getBody());
        if (HttpStatus.OK != result.getStatusCode()) {
            Integer statusCode = result.getStatusCode().value();
            throw new Exception("Spatial server call exception :: " + statusCode);
        }

        return resultBody;
    }

    private HttpEntity<MultiValueMap<String, Object>> getRequestEntity(MultiValueMap<String, Object> body, MediaType contentType) {
        String token = mapStudioProp.getToken();
        HttpHeaders headers = new HttpHeaders();
        if (contentType != null) {
            headers.setContentType(contentType);
        }
        if (ValidUtil.notEmpty(token)) {
            headers.setBearerAuth(token);
        }

        return new HttpEntity<>(body, headers);
    }

}
