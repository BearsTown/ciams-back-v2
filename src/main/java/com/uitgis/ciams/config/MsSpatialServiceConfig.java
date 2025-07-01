package com.uitgis.ciams.config;


import com.uitgis.spatial.adpater.mapstudio.service.MsLayerInfoService;
import com.uitgis.spatial.dto.SpatialConfig;
import com.uitgis.spatial.service.SpatialLayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class MsSpatialServiceConfig {

    private final MapStudioProp mapStudioProp;


    @Bean("spatialConfig")
    public SpatialConfig spatialConfig() {
        return SpatialConfig.builder()
                .url(mapStudioProp.getUrl())
                .userName(mapStudioProp.getUser())
                .accessToken(mapStudioProp.getToken())
                .build();
    }

    @Bean("spatialLayerService")
    public SpatialLayerService mapStudioLayerService() {
        return new MsLayerInfoService(spatialConfig());
    }

}
