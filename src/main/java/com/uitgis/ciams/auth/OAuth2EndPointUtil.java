package com.uitgis.ciams.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public class OAuth2EndPointUtil {

    static final String ACCESS_TOKEN_REQUEST_ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";


    public static MultiValueMap<String, String> getParameters(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>(parameterMap.size());
        parameterMap.forEach((key, values) -> {
            for (String value : values) {
                parameters.add(key, value);
            }
        });
        return parameters;
    }

    public static void throwError(String errorCode, String parameterName, String errorUri){

        OAuth2Error error = new OAuth2Error(errorCode, "OAuth 2.0 Parameter: "+parameterName, errorUri);
        throw new OAuth2AuthenticationException(error);
    }
}

