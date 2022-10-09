package com.livecat.oauth2;

import com.livecat.util.base.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Logout success handler and clear the data in redis
 */
@Component("customLogoutSuccessHandler")
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        // Get access token
        String accessToken = request.getParameter("accessToken");
        if(StringUtils.isNotBlank(accessToken)){
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
            if(oAuth2AccessToken != null){
                // Delete the corresponding access token in redis
                tokenStore.removeAccessToken(oAuth2AccessToken);
            }
        }
        // Logout success, return result
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write( Result.ok().toJsonString() );
    }
}
