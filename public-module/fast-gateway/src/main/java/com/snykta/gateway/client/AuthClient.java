package com.snykta.gateway.client;

import com.snykta.security.token.BasicToken;
import com.snykta.tools.constant.AuthConstant;
import com.snykta.tools.constant.WebConstant;
import com.snykta.tools.web.result.Ret;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

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
    Ret<BasicToken> validateToken(@RequestHeader(AuthConstant.head_token_key) String token);





}
