package com.livecat.oauth2.config;

import com.livecat.dto.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Map;

public class AuthUtil {
    public static SysUser getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        Map<String, Object> map = (Map<String, Object>) details.getDecodedDetails();
        Map<String, String> userInfo = (Map<String, String>) map.get("userInfo");

        SysUser user = new SysUser();
        user.setId(userInfo.get("uid"));
        user.setNickName(userInfo.get("nickName"));
        user.setUsername( userInfo.get("username") );
        user.setEmail( userInfo.get("email") );
        user.setMobile( userInfo.get("mobile"));

        return user;
    }
}
