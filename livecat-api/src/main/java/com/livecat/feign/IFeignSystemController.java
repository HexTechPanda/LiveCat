package com.livecat.feign;

import com.livecat.dto.SysUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "system-server", path = "/system")
public interface IFeignSystemController {
    @ApiImplicitParam(name = "username", value = "username", required = true)
    @ApiOperation("Feign Interface - query user info by username.")
    @GetMapping("/api/feign/user/{username}")
    SysUser findUserByUsername(@PathVariable("username") String username);
}
