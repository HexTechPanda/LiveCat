package com.livecat.oauth2.config;

import com.alibaba.fastjson.JSON;
import com.livecat.oauth2.service.JwtUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        System.out.println("start enhance OAuth2AccessToken");
        JwtUser user = (JwtUser) oAuth2Authentication.getPrincipal();
        Map<String, Object> map = new HashMap<>();
        map.put("userInfo", JSON.toJSON(user));
        // set additional information
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(map);
        System.out.println("end enhance OAuth2AccessToken");
        return oAuth2AccessToken;
    }
}
