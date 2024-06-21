package com.snykta.auth.service.impl;


import com.snykta.auth.client.SystemClient;
import com.snykta.auth.dto.SysUserDto;
import com.snykta.auth.service.IAuthService;
import com.snykta.starter.basic.web.web.service.BaseService;
import com.snykta.starter.security.token.BasicAuthToken;
import com.snykta.starter.security.utils.CyTokenUtil;
import com.snykta.starter.tools.exception.ServiceException;
import com.snykta.starter.tools.utils.CyObjUtil;
import com.snykta.starter.tools.web.result.Ret;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends BaseService implements IAuthService {

    private final SystemClient systemClient;

    public AuthService(SystemClient systemClient) {
        this.systemClient = systemClient;
    }

    /**
     * 登录
     * @param phoneNumber
     * @param password
     * @return
     */
    @Override
    public String doLogin(String phoneNumber, String password) {
        Ret<SysUserDto> result = systemClient.doLogin(phoneNumber, password);
        if (Ret.isError(result)) {
            throw new ServiceException(result);
        }
        SysUserDto sysUserDto = result.getData();
        if (CyObjUtil.isNull(sysUserDto)) {
            throw new ServiceException("登录失败");
        }
        BasicAuthToken basicAuthToken = new BasicAuthToken();
        basicAuthToken.setUserId(sysUserDto.getId());
        basicAuthToken.setUserName(sysUserDto.getNickName());
        basicAuthToken.setUserNumber(sysUserDto.getPhoneNumber());
        basicAuthToken.setRightCodeList(sysUserDto.getRightCodeList());
        basicAuthToken.setRoleCodeList(sysUserDto.getRoleCodeList());

        return CyTokenUtil.createToken(basicAuthToken);
    }

    /**
     * 注册
     * @param sysUserDto
     */
    @Override
    public void doRegister(SysUserDto sysUserDto) {
        if (CyObjUtil.isNull(sysUserDto)) {
            throw new ServiceException("注册信息不能为空");
        }

        systemClient.doRegister(sysUserDto);

    }





}
