package com.snykta.gateway.filter;



import com.snykta.gateway.GatewayConstant;
import com.snykta.gateway.client.AuthClient;
import com.snykta.gateway.utils.WebFluxUtil;
import com.snykta.security.token.BasicToken;
import com.snykta.tools.constant.AuthConstant;
import com.snykta.tools.utils.CyExceptionUtil;
import com.snykta.tools.utils.CyStrUtil;
import com.snykta.tools.web.result.ResultCode;
import com.snykta.tools.web.result.Ret;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Gateway认证授权
 */

@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {


    private final AuthClient authClient;
    public AuthFilter(AuthClient authClient) {
        this.authClient = authClient;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();

        // 请求URL
        String url = request.getURI().getPath();
        // 忽略不校验的url
        if (GatewayConstant.ignoreUrlList.stream().anyMatch(u -> CyStrUtil.equalsIgnoreCase(url, u))) {
            return chain.filter(exchange);
        }
        try {
            String token = request.getHeaders().getFirst(AuthConstant.head_token_key);
            if (CyStrUtil.isEmpty(token)) {
                return WebFluxUtil.webFluxResponseWriter(exchange.getResponse(), HttpStatus.UNAUTHORIZED, Ret.fail(ResultCode.UN_AUTHORIZED, "未认证，请登录"));
            }
            Ret<BasicToken> ret = authClient.validateToken(token);
            if (Ret.isError(ret)) {
                return WebFluxUtil.webFluxResponseWriter(exchange.getResponse(), HttpStatus.UNAUTHORIZED, ret);
            }
        } catch (Exception e) {
            log.error("\r\ngateway处理器异常：" + CyExceptionUtil.getStackMsg(e));
            return WebFluxUtil.webFluxResponseWriter(exchange.getResponse(), HttpStatus.INTERNAL_SERVER_ERROR, Ret.fail(ResultCode.ERROR, e.getMessage()));
        }


        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }




    @Override
    public int getOrder() {
        return -200;
    }
}
