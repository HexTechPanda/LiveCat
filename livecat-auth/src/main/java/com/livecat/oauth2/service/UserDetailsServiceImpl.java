package com.livecat.oauth2.service;

import com.livecat.dto.SysUser;
import com.livecat.feign.IFeignSystemController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IFeignSystemController feignSystemController;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. if username empty
        if(StringUtils.isEmpty(username)){
            throw new BadCredentialsException("Username should not be empty!");
        }
        // 2. find user by username
        SysUser sysUser = feignSystemController.findUserByUsername(username);
        if(sysUser == null){
            throw new BadCredentialsException("username or password error");
        }
        // 3. use id to find authority
        // 4. encapsulate authority info(authority code)
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("customer"));
        // 5. build UserDetails Interface's implement class JwtUser
        JwtUser jwtUser = new JwtUser(sysUser.getId(),
                sysUser.getUsername(),
                sysUser.getPassword(),
                sysUser.getNickName(),
                sysUser.getMobile(),
                sysUser.getEmail(),
                sysUser.getIsAccountNonExpired(),
                sysUser.getIsAccountNonLocked(),
                sysUser.getIsCredentialsNonExpired(),
                sysUser.getIsEnabled(),
                authorities);
        return jwtUser;
    }
}
