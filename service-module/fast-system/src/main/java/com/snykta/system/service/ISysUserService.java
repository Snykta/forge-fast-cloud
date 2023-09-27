package com.snykta.system.service;


import com.snykta.system.dto.SysUserDto;
import com.snykta.system.entity.SysUserEntity;
import com.snykta.tools.dto.TokenUserInfo;

/**
 * 用户信息表
 *
 * @author Snykta
 * @date 2023-09-27
 */
public interface ISysUserService {

    /**
     * 用户登录
     * @param phoneNumber
     * @param password
     * @return
     */
    TokenUserInfo doLogin(String phoneNumber, String password);

    /**
     * 注册
     * @param sysUserDto
     */
    void doRegister(SysUserDto sysUserDto);

}