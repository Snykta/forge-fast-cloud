package com.snykta.system.controller;


import com.snykta.basic.web.web.controller.BaseController;
import com.snykta.system.dto.SysUserDto;
import com.snykta.system.service.IAuthUserService;
import com.snykta.tools.web.result.Ret;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 认证
 */
@RestController
@RequestMapping("auth")
@Api(tags="认证")
public class AuthUserController extends BaseController {


    private final IAuthUserService authUserService;

    public AuthUserController(IAuthUserService authUserService) {
        this.authUserService = authUserService;
    }


    /**
     * 登录
     * @param phoneNumber
     * @param password
     * @return
     */
    @ApiOperation("登录")
    @PostMapping(value = "/doLogin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Ret<SysUserDto> doLogin(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("password") String password) {
        return Ret.success(authUserService.doLogin(phoneNumber, password));
    }

    /**
     * 注册
     * @param sysUserDto
     * @return
     */
    @ApiOperation("注册")
    @PostMapping(value = "/doRegister", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Ret<Void> doRegister(@RequestBody SysUserDto sysUserDto) {
        authUserService.doRegister(sysUserDto);
        return Ret.success();
    }


}
