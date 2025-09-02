package com.uitgis.ciams.config;


import com.uitgis.ciams.exception.CustomException;
import com.uitgis.ciams.model.CiamsConfig;
import com.uitgis.ciams.admin.service.AdminConfigService;
import com.uitgis.ext.kras.dto.KrasConfig;
import com.uitgis.openapi.kgeo.KGeoConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {
    private static String krasKey;
    private static String krasUrl;
    private static KrasConfig.KrasMode krasMode;
    private final AdminConfigService adminConfigService;

    @Bean
    @Profile({"local", "dev"})
    public String dev() {
        log.info("OpenApiConfig DEV @@@@@@@@@@@@@@");

        krasMode = KrasConfig.KrasMode.TEST;

        KGeoConfig.setApiMode(KGeoConfig.API_MODE_TYPE.TEST);

        return krasMode.toString();
    }

    @Bean
    @Profile("prod")
    public String prod() {
        log.info("OpenApiConfig PROD @@@@@@@@@@@@@@");

        try {
            setKrasConfig();
            krasMode = KrasConfig.KrasMode.PROD;
        } catch (CustomException e) {
            log.error("KRAS ERROR : {}", e.getMessage());
        }

        try {
            setKgeoConfig();
            KGeoConfig.setApiMode(KGeoConfig.API_MODE_TYPE.PROD);
        } catch (CustomException e) {
            log.error("K-Geo ERROR : {}", e.getMessage());
        }

        return krasMode.toString();
    }

    private void setKrasConfig() throws CustomException {
        krasUrl = adminConfigService.getConfById("KRAS_URL")
                .map(CiamsConfig::getConfValue)
                .orElseThrow(() -> new CustomException("KRAS_URL does not config."));

        krasKey = adminConfigService.getConfById("KRAS_SYS_ID")
                .map(CiamsConfig::getConfValue)
                .orElseThrow(() -> new CustomException("KRAS_SYS_ID does not config."));
    }

    private void setKgeoConfig() throws CustomException {
//        KGeoConfig.API_MODE_TYPE kGeoMode = ciamsConfigService.getConfById("KGEO_API_USE")
//                .map(CiamsConfig::getUsed)
//                .map(bool -> {
//                    if (bool) {
//                        return KGeoConfig.API_MODE_TYPE.PROD;
//                    } else {
//                        return KGeoConfig.API_MODE_TYPE.TEST;
//                    }
//                })
//                .orElseThrow(() -> new CustomException("KGEO_API_USE does not config."));

        String kGeoUrl = adminConfigService.getConfById("KGEO_API_URL")
                .map(CiamsConfig::getConfValue)
                .orElseThrow(() -> new CustomException("KGEO_API_URL does not config."));

        String kGeoKey = adminConfigService.getConfById("KGEO_API_KEY")
                .map(CiamsConfig::getConfValue)
                .orElseThrow(() -> new CustomException("KGEO_API_KEY does not config."));

//        KGeoConfig.setApiMode(kGeoMode);
        KGeoConfig.setApiUrl(kGeoUrl);
        KGeoConfig.setApiKey(kGeoKey);
    }

    public static KrasConfig.DataParam getKrasParam() {
        KrasConfig.DataParam dataParam = new KrasConfig.DataParam();
        dataParam.setKey(krasKey);
        dataParam.setUrl(krasUrl);
        dataParam.setMode(krasMode);
        return dataParam;
    }
}
