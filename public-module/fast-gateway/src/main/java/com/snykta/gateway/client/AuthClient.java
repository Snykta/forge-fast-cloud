package com.snykta.gateway.client;

import com.snykta.security.token.BasicToken;
import com.snykta.tools.web.result.Ret;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 认证授权服务
 */
@FeignClient("fast-auth")
public interface AuthClient {

    /**
     * 校验Token
     * @return
     */
    @PostMapping(value = "auth/validateToken")
    Ret<BasicToken> validateToken();





}
