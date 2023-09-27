package com.snykta.auth.service;

import com.snykta.auth.dto.SysUserDto;


public interface IAuthService {

    /**
     * 用户登录 并返回token
     * @param phoneNumber
     * @param password
     * @return
     */
    String doLogin(String phoneNumber, String password);

    /**
     * 注册
     * @param sysUserDto
     */
    void doRegister(SysUserDto sysUserDto);


}
