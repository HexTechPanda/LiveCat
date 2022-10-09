package com.livecat.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class JwtTokenStoreConfig {

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(new ClassPathResource("oauth2.jks"), "oauth2".toCharArray());
        converter.setKeyPair(factory.getKeyPair("oauth2"));
        return converter;
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public TokenStore tokenStore(){
        // use JWT to manage tokens
        return new JwtTokenStore(jwtAccessTokenConverter()){
            // store JWT token in redis
            @Override
            public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
                // set JWT's unique id jti as redis key, value is accessToken
                if(token.getAdditionalInformation().containsKey("jti")){
                    String jti = token.getAdditionalInformation().get("jti").toString();
                    // store in redis(key, value, valid time, time unit)
                    redisTemplate.opsForValue().set(jti, token.getValue(), token.getExpiresIn(), TimeUnit.SECONDS);
                }
                super.storeAccessToken(token, authentication);
            }
            // delete jwt token in redis
            @Override
            public void removeAccessToken(OAuth2AccessToken token) {
                if(token.getAdditionalInformation().containsKey("jti")){
                    String jti = token.getAdditionalInformation().get("jti").toString();
                    // delete record in redis by key: jti
                    redisTemplate.delete(jti);
                }
                super.removeAccessToken(token);
            }
        };
    }
}
