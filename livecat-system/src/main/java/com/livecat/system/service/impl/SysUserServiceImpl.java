package com.livecat.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.livecat.dto.SysUser;
import com.livecat.system.mapper.SysUserMapper;
import com.livecat.system.req.RegisterREQ;
import com.livecat.system.service.ISysUserService;
import com.livecat.util.base.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SysUser findByUsername(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public Result checkUsername(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        SysUser sysUser = baseMapper.selectOne(wrapper);
        return Result.ok( sysUser == null ? false : true );
    }

    @Override
    public Result register(RegisterREQ req) {
        if(StringUtils.isEmpty( req.getUsername() )) {
            return Result.error("Username can not be empty.");
        }

        if(StringUtils.isEmpty( req.getPassword() )) {
            return Result.error("Password can not be empty.");
        }

        if(StringUtils.isEmpty( req.getRepPassword() )) {
            return Result.error("Confirm password can not be empty.");
        }

        if( !StringUtils.equals(req.getPassword(), req.getRepPassword())) {
            return Result.error("The two entered passwords do not match.");
        }

        // Check if username exists
        Result result = checkUsername(req.getUsername());
        if( (Boolean) result.getData() ) {
            return Result.error("Username has already been registered, please change a username.");
        }

        SysUser sysUser = new SysUser();
        sysUser.setUsername( req.getUsername() );
        sysUser.setPassword( passwordEncoder.encode( req.getPassword() ) );
        sysUser.setIsAccountNonLocked(1);
        sysUser.setIsAccountNonLocked(1);
        sysUser.setIsCredentialsNonExpired(1);
        sysUser.setIsEnabled(1);
        sysUser.setNickName( req.getNickName() );
        sysUser.setMobile( req.getMobile() );
        sysUser.setEmail( req.getEmail() );
        sysUser.setCreateDate( new Date() );
        sysUser.setUpdateDate( new Date() );
        sysUser.setPwdUpdateDate( new Date() );

        try {
            this.save(sysUser);
        } catch (DuplicateKeyException ex){
            return Result.error("Mobile or email has registered.");
        }

        return Result.ok();
    }
}
