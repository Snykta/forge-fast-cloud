package com.snykta.auth.service.impl;


import com.snykta.auth.client.SystemClient;
import com.snykta.auth.dto.SysUserDto;
import com.snykta.auth.service.IAuthService;
import com.snykta.basic.web.web.service.BaseService;
import com.snykta.security.token.BasicToken;
import com.snykta.security.utils.CyTokenUtil;
import com.snykta.tools.exception.ServiceException;
import com.snykta.tools.utils.CyObjUtil;
import com.snykta.tools.web.result.Ret;
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
        BasicToken basicToken = new BasicToken();
        basicToken.setUserId(sysUserDto.getId());
        basicToken.setUserName(sysUserDto.getNickName());
        basicToken.setUserNumber(sysUserDto.getPhoneNumber());
        basicToken.setRightCodeList(sysUserDto.getRightCodeList());
        basicToken.setRoleCodeList(sysUserDto.getRoleCodeList());

        return CyTokenUtil.createToken(basicToken);
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
        Ret<String> ret = systemClient.doRegister(sysUserDto);
        if (Ret.isError(ret)) {
            throw new ServiceException(ret);
        }


    }





}
