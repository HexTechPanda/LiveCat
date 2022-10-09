package com.livecat.system.api;

import com.livecat.system.req.RegisterREQ;
import com.livecat.system.service.ISysUserService;
import com.livecat.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "User Management API", description = "User Management API, can be called without authentication")
@RestController
@RequestMapping("/api/user")
public class ApiSysUserController {
    @Autowired
    private ISysUserService sysUserService;

    @ApiImplicitParam(name = "username", value = "username", required = true)
    @ApiOperation("Check username API")
    @GetMapping("/username/{username}") //  /api/user/username/{username}
    public Result checkUsername(@PathVariable("username") String username) {
        return sysUserService.checkUsername(username);
    }

    @ApiOperation("Register API")
    @PostMapping("/register") // /api/user/register
    public Result register(@RequestBody @Valid RegisterREQ req) {
        return sysUserService.register(req);
    }
}
