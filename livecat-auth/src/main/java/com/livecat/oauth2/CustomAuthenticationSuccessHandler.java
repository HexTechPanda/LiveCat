package com.livecat.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.livecat.util.base.Result;
import com.livecat.util.enums.ResultEnum;
import com.livecat.util.tools.RequestUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Success handler, verifies client information, generates jwt token, and responds to result data
 */
@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    Logger logger = LoggerFactory.getLogger(getClass());private static final String HEADER_TYPE = "Basic ";

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        logger.info("Login success {}", authentication.getPrincipal());
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        logger.info("header {}", header);

        Result result = null;
        try {
            if(header == null || !header.startsWith(HEADER_TYPE)){
                throw new UnsupportedOperationException("No client info in request header.");
            }
            // get new auth info
            String[] tokens = RequestUtil.extractAndDecodeHeader(header);
            assert tokens.length == 2;
            String clientId = tokens[0];
            String clientSecret = tokens[1];
            // Query client information to check whether it is valid
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
            if(clientDetails == null){
                throw new UnsupportedOperationException("clientId not exist");
            }
            // Check if the client password is valid
            if(!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())){
                throw new UnsupportedOperationException("invalid clientSecret");
            }
            // Combine the request object to get the token
            TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "custom");
            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
            // get access token object
            OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
            result = Result.ok(accessToken);
        } catch(Exception e) {
            logger.error("AuthenticationSuccessHandler Exception={}", e.getMessage(), e);
            result = Result.build(ResultEnum.AUTH_FAIL.getCode(), e.getMessage());
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write( objectMapper.writeValueAsString( result ) );
    }
}
