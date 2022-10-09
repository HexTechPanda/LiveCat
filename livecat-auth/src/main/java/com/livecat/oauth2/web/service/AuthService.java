package com.livecat.oauth2.web.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.livecat.util.base.Result;
import com.livecat.util.enums.ResultEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * Get new token by refreshToken
     * @param header Authentication Header, Base clientId:clientPassword
     * @param refreshToken
     * @return
     */
    public Result refreshToken(String header, String refreshToken) throws HttpProcessException {
        // get ip and port from Nacos
        ServiceInstance serviceInstance = loadBalancerClient.choose("auth-server");
        if(serviceInstance == null){
            return Result.error("Available service not found. Please try again");
        }
        String refreshTokenUrl = serviceInstance.getUri().toString() + "/auth/oauth/token";

        Map<String, Object> map = new HashMap<>();
        map.put("grant_type", "refresh_token");
        map.put("refresh_token", refreshToken);

        Header[] headers = HttpHeader.custom()
                .contentType(HttpHeader.Headers.APP_FORM_URLENCODED)
                .authorization(header)
                .build();

        HttpConfig config = HttpConfig.custom().headers(headers).url(refreshTokenUrl).map(map);

        String token = HttpClientUtil.post(config);
        JSONObject jsonToken = JSON.parseObject(token);
        if(StringUtils.isNotEmpty(jsonToken.getString("error"))){
            return Result.build(ResultEnum.TOKEN_PAST);
        }
        return Result.ok(jsonToken);
    }
}
