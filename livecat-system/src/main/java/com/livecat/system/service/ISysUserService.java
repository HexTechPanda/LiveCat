package com.livecat.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.livecat.dto.SysUser;
import com.livecat.system.req.RegisterREQ;
import com.livecat.util.base.Result;

public interface ISysUserService extends IService<SysUser> {
    SysUser findByUsername(String username);
    Result checkUsername(String username);
    Result register(RegisterREQ req);
}
