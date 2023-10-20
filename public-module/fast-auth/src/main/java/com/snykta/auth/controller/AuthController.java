package com.snykta.auth.controller;


import com.snykta.auth.dto.SysUserDto;
import com.snykta.auth.service.IAuthService;
import com.snykta.basic.web.annotation.RateLimiter;
import com.snykta.basic.web.web.controller.BaseController;
import com.snykta.security.token.BasicToken;
import com.snykta.security.utils.CyTokenUtil;
import com.snykta.tools.web.result.Ret;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping(value = "auth")
@Slf4j
public class AuthController extends BaseController {

    private final IAuthService authService;
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }


    /**
     * 登录并且返回token
     * @param phoneNumber
     * @param password
     * @return
     */
    @PostMapping(value = "/doLogin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Ret<String> doLogin(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("password") String password) {
        return Ret.success(authService.doLogin(phoneNumber, password));
    }

    /**
     * 注册
     * @param sysUserDto
     * @return
     */
    @PostMapping(value = "/doRegister", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Ret<Void> doRegister(@RequestBody SysUserDto sysUserDto) {
        authService.doRegister(sysUserDto);
        return Ret.success();
    }

    /**
     * 退出登录
     * @return
     */
    @GetMapping(value = "/doLogout")
    public Ret<Void> doLogout() {
        CyTokenUtil.doLogout();
        return Ret.success();
    }


    /**
     * 刷新token，将返回新的token
     * @return
     */
    @RateLimiter(value = 1, timeout = 5, timeUnit = TimeUnit.SECONDS)
    @PostMapping("/refreshToken")
    public Ret<String> refreshToken() {
        return Ret.success(CyTokenUtil.refreshToken());
    }


    /**
     * 校验Token
     * token放在请求头中
     * @return 返回用户信息数据
     */
    @PostMapping("/validateToken")
    public Ret<BasicToken> validateToken() {
        return Ret.success(CyTokenUtil.validateToken());
    }


}
