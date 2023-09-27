package com.snykta.auth.service.impl;

import com.snykta.auth.client.SystemClient;
import com.snykta.auth.dto.SysUserDto;
import com.snykta.auth.service.IAuthService;
import com.snykta.basic.web.web.service.BaseService;
import com.snykta.tools.dto.TokenUserInfo;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends BaseService implements IAuthService {

    private final SystemClient systemClient;

    public AuthService(SystemClient systemClient) {
        this.systemClient = systemClient;
    }

    /**
     * 用户登录
     * @param phoneNumber
     * @param password
     * @return
     */
    @Override
    public String doLogin(String phoneNumber, String password) {

        TokenUserInfo data = systemClient.doLogin(phoneNumber, password).getData();



        return null;
    }

    /**
     * 用户注册
     * @param sysUserDto
     */
    @Override
    public void doRegister(SysUserDto sysUserDto) {

    }
}
