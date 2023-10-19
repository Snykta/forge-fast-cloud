package com.snykta.system.service;

import com.snykta.system.dto.SysUserDto;


/**
 * 认证
 */
public interface IAuthUserService {

    /**
     * 登录
     * @param phoneNumber
     * @param password
     * @return
     */
    SysUserDto doLogin(String phoneNumber, String password);

    /**
     * 注册
     * @param sysUserDto
     */
    void doRegister(SysUserDto sysUserDto);
}
