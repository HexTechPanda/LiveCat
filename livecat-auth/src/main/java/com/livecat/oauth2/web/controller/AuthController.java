package com.livecat.oauth2.web.controller;

import com.google.common.base.Preconditions;
import com.livecat.oauth2.web.service.AuthService;
import com.livecat.util.base.Result;
import com.livecat.util.tools.RequestUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {
    Logger logger = LoggerFactory.getLogger(getClass());

    private static final String HEADER_TYPE = "Basic ";

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @GetMapping("/user/refreshToken") // localhost:7001/auth/user/refreshToken?refreshToken=xxx
    public Result refreshToken(HttpServletRequest request){
        try {
            // get refreshToken from request
            String refreshToken = request.getParameter("refreshToken");
            // get request header
            Preconditions.checkArgument(StringUtils.isNotEmpty(refreshToken), "refreshToken should not be empty.");
            // query client info, check if enable
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            if(header == null || !header.startsWith(HEADER_TYPE)){
                throw new UnsupportedOperationException("No client info in request header.");
            }
            // get new auth info
            String[] tokens = RequestUtil.extractAndDecodeHeader(header);
            assert tokens.length == 2;
            String clientId = tokens[0];
            String clientSecret = tokens[1];

            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
            if(clientDetails == null){
                throw new UnsupportedOperationException("clientId not exist");
            }
            if(!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())){
                throw new UnsupportedOperationException("invalid clientSecret");
            }
            return authService.refreshToken(header, refreshToken);
        } catch(Exception e) {
            logger.error("refreshToken={}", e.getMessage(), e);
            return Result.error("Failed to get new token." + e.getMessage());
        }
    }
}
