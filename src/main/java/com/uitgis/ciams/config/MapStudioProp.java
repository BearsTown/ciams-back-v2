package com.uitgis.ciams.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("mapstudio")
public class MapStudioProp {
	private String url;
	private String user;
	private String token;
	private String apiData;
	private String apiLayerInfo;
	private String apiLayerTable;
}
