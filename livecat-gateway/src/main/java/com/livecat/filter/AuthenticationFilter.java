package com.livecat.filter;

import net.minidev.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    private static final String[] white = { "/api/" };
    /**
     * Defines whether the authentication request header has Authorization
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String path = request.getPath().pathWithinApplication().value();

        // Open API interface for release without authentication
        if(StringUtils.indexOfAny(path, white) != -1) {
            return chain.filter(exchange);
        }
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if(StringUtils.isEmpty(authorization)) {
            // If there is no authorization request header, it will respond with an error message
            JSONObject message = new JSONObject();
            message.put("code", 1401);
            message.put("message", "no authorization");

            // Convert the response message content object to bytes
            byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().add(HttpHeaders.CONTENT_TYPE,  "application/json;charset=UTF-8");
            // return response object
            return response.writeWith( Mono.just(buffer) );
        }
        // If the request header is not empty, the verification is passed and this filter is released
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // Filter execution order, the smaller the priority.
        return 0;
    }
}
