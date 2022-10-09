package com.livecat.system.feign;

import com.livecat.dto.SysUser;
import com.livecat.feign.IFeignSystemController;
import com.livecat.system.service.ISysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Feign System API")
@RestController
public class FeignSystemController implements IFeignSystemController {
    @Autowired
    private ISysUserService sysUserService;

    @Override
    public SysUser findUserByUsername(String username) {
        return sysUserService.findByUsername(username);
    }
}
