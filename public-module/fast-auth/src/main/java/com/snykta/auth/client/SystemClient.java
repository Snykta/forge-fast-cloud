package com.snykta.auth.client;

import com.snykta.auth.dto.SysUserDto;
import com.snykta.tools.dto.TokenUserInfo;
import com.snykta.tools.web.result.Ret;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * fast-system 微服务
 */
@FeignClient(value = "fast-system")
public interface SystemClient {

    /**
     * 注册
     * @param sysUserDto
     * @return
     */
    @PostMapping(value = "sysUser/doRegister", consumes = MediaType.APPLICATION_JSON_VALUE)
    Ret<Void> doRegister(@RequestBody SysUserDto sysUserDto);


    /**
     * 登录
     * @param phoneNumber
     * @param password
     * @return
     */
    @PostMapping(value = "sysUser/doLogin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    Ret<TokenUserInfo> doLogin(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("password") String password);






}
