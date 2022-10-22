package com.livecat.oauth2.service;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
public class JwtUser implements UserDetails {
    @ApiModelProperty(value = "User ID")
    private String uid;

    @ApiModelProperty(value = "username")
    private String username;

    @JSONField(serialize = false) // ignored in json
    @ApiModelProperty(value = "password, encryption, admin/1234")
    private String password;

    @ApiModelProperty(value = "nickName")
    private String nickName;

    @ApiModelProperty(value = "mobile")
    private String mobile;

    @ApiModelProperty(value = "email")
    private String email;

    // 1 true 0 false
    @JSONField(serialize = false) // ignore transform to json
    @ApiModelProperty(value = "isAccountNonExpired(1 not expire, 0 expire)")
    private boolean isAccountNonExpired; // 不要写小写 boolean

    @JSONField(serialize = false) // ignore transform to json
    @ApiModelProperty(value = "isAccountNonLocked(1 not locked, 0 locked)")
    private boolean isAccountNonLocked;

    @JSONField(serialize = false) // ignore transform to json
    @ApiModelProperty(value = "isCredentialsNonExpired(1 not expire, 0 expire)")
    private boolean isCredentialsNonExpired;

    @JSONField(serialize = false) // ignore transform to json
    @ApiModelProperty(value = "isEnabled(1 enable，0 unable)")
    private  boolean isEnabled;

    /**
     * GrantedAuthority
     */
    @JSONField(serialize = false) // // ignore transform to json
    private List<GrantedAuthority> authorities;

    //    isAccountNonExpired is Integer accepted, then transform to boolean
    public JwtUser(String uid, String username, String password,
                   String nickName, String mobile, String email,
                   Integer isAccountNonExpired, Integer isAccountNonLocked,
                   Integer isCredentialsNonExpired, Integer isEnabled,
                   List<GrantedAuthority> authorities) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.mobile = mobile;
        this.email = email;
        this.isAccountNonExpired = isAccountNonExpired == 1 ? true: false;
        this.isAccountNonLocked = isAccountNonLocked == 1 ? true: false;
        this.isCredentialsNonExpired = isCredentialsNonExpired == 1 ? true: false;
        this.isEnabled = isEnabled == 1 ? true: false;
        this.authorities = authorities;
    }
}
