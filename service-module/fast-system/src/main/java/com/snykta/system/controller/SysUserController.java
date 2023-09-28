package com.snykta.system.controller;


import com.snykta.basic.web.web.controller.BaseController;
import com.snykta.tools.dto.TokenUserInfo;
import com.snykta.tools.web.page.PageDto;
import com.snykta.tools.web.result.Ret;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.snykta.system.dto.SysUserDto;
import com.snykta.system.service.ISysUserService;



/**
 * 用户信息表
 *
 * @author Snykta
 * @date 2023-09-27
 */
@RestController
@RequestMapping("sysUser")
@Api(tags="用户信息表")
public class SysUserController extends BaseController {

    private final ISysUserService sysUserService;

    public SysUserController(ISysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }


    /**
     * 用户登录
     * @param phoneNumber
     * @param password
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping(value = "/doLogin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Ret<TokenUserInfo> doLogin(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("password") String password) {
        return Ret.success(sysUserService.doLogin(phoneNumber, password));
    }

    /**
     * 用户注册
     * @param sysUserDto
     * @return
     */
    @ApiOperation("用户注册")
    @PostMapping(value = "/doRegister", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Ret<Void> doRegister(@RequestBody SysUserDto sysUserDto) {
        sysUserService.doRegister(sysUserDto);
        return Ret.success("注册成功");
    }




}