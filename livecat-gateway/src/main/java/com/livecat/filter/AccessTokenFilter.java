package com.livecat.filter;

import com.nimbusds.jose.JWSObject;
import net.minidev.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

@Component
public class AccessTokenFilter implements GlobalFilter, Ordered {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * Check whether the token in the request header is valid,
     * check whether it exists in redis,
     * if it does not exist, it is invalid jwt.
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // get token
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String token = StringUtils.substringAfter(authorization, "Bearer ");

        if(StringUtils.isEmpty(token)) {
            // If it is empty, it may be a whitelisted request, then let it go directly
            return chain.filter(exchange);
        }
        // error messages
        String message = null;

        JWSObject jwsObject = null;
        try {
            jwsObject = JWSObject.parse(token);
            JSONObject jsonObject = jwsObject.getPayload().toJSONObject();

            // Check whether there is a token corresponding to jti in redis
            String jti = jsonObject.get("jti").toString();
            // Check if it exists
            Object value = redisTemplate.opsForValue().get(jti);
            if(value == null) {
                logger.info("Token has expired. {}", token);
                message = "Your identity has expired, please re-authenticate!";
            }
        } catch (ParseException e) {
            logger.error("Failed to parse token {}", token);
            message = "Invalid token.";
        }

        if(message == null) {
            // If the token exists, pass
            return chain.filter(exchange);
        }

        // build error message object
        JSONObject result = new JSONObject();
        result.put("code", 1401);
        result.put("message", message);

        // Convert the response message content object to bytes
        byte[] bits = result.toJSONString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE,  "application/json;charset=UTF-8");
        // return response object
        return response.writeWith( Mono.just(buffer) );
    }

    @Override
    public int getOrder() {
        // This AccessTokenFilter filter is executed after AuthenticationFilter
        return 10;
    }
}
