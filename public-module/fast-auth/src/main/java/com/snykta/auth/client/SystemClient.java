package com.snykta.auth.client;

import com.snykta.auth.dto.SysUserDto;
import com.snykta.tools.web.result.Ret;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * fast-system 微服务
 */
@FeignClient("fast-system")
public interface SystemClient {

    /**
     * 登录
     * @param phoneNumber
     * @param password
     * @return
     */
    @PostMapping(value = "sysUser/doLogin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    Ret<SysUserDto> doLogin(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("password") String password);


    /**
     * 注册
     * @param sysUserDto
     * @return
     */
    @PostMapping(value = "sysUser/doRegister", consumes = MediaType.APPLICATION_JSON_VALUE)
    Ret<String> doRegister(@RequestBody SysUserDto sysUserDto);








}
