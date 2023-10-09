package com.snykta.system.service;


import com.snykta.system.dto.SysUserDto;
import com.snykta.system.entity.SysUserEntity;

/**
 * 用户信息表
 *
 * @author Snykta
 * @date 2023-09-27
 */
public interface ISysUserService {

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