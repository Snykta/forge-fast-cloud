package com.snykta.starter.basic.web.aspect;

import com.snykta.starter.basic.web.annotation.RateLimiter;
import com.snykta.starter.basic.web.web.utils.IpUtil;
import com.snykta.starter.tools.exception.ServiceException;
import com.snykta.starter.tools.utils.CyStrUtil;
import com.snykta.starter.redis.config.RedisAutoConfig;
import com.snykta.starter.tools.constant.AuthConstant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
@ConditionalOnClass({StringRedisTemplate.class, RedisScript.class, RedisTemplate.class, Servlet.class, RedisAutoConfig.class})
public class RateLimiterAspect {
    private final static String SEPARATOR = ":";
    private final static String REDIS_LIMIT_KEY_PREFIX = "limit:";
    private final StringRedisTemplate stringRedisTemplate;
    private final RedisScript<Long> limitRedisScript;

    public RateLimiterAspect(StringRedisTemplate stringRedisTemplate, RedisScript<Long> limitRedisScript) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.limitRedisScript = limitRedisScript;
    }

    @Pointcut("@annotation(com.snykta.starter.basic.web.annotation.RateLimiter)")
    public void rateLimit() {

    }

    @Around("rateLimit()")
    public Object pointcut(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        RateLimiter rateLimiter = AnnotationUtils.findAnnotation(method, RateLimiter.class);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        if (rateLimiter != null) {
            String key = rateLimiter.key();
            // 默认用类名+方法名做限流的 key 前缀
            if (CyStrUtil.isBlank(key)) {
                key = method.getDeclaringClass().getName() + CyStrUtil.DOT + method.getName();
            }
            // 最终限流的 key 为 前缀 + IP地址 + token信息
            String ipAddr = CyStrUtil.isEmpty(IpUtil.getIpAddr()) ? "无IP" : IpUtil.getIpAddr();
            key = key + SEPARATOR + ipAddr + "|" + request.getHeader(AuthConstant.head_token_key);

            long max = rateLimiter.max();
            long timeout = rateLimiter.timeout();
            TimeUnit timeUnit = rateLimiter.timeUnit();
            boolean limited = shouldLimited(key, max, timeout, timeUnit);
            if (limited) {
                throw new ServiceException("请求过快，请稍后再试");
            }
        }

        return point.proceed();
    }

    private boolean shouldLimited(String key, long max, long timeout, TimeUnit timeUnit) {
        // 最终的 key 格式为：
        // limit:类名.方法名:IP:token
        key = REDIS_LIMIT_KEY_PREFIX + key;
        // 统一使用单位毫秒
        long ttl = timeUnit.toMillis(timeout);
        // 当前时间毫秒数
        long now = Instant.now().toEpochMilli();
        long expired = now - ttl;
        // 注意这里必须转为 String,否则会报错 java.lang.Long cannot be cast to java.lang.String
        Long executeTimes = stringRedisTemplate.execute(limitRedisScript, Collections.singletonList(key), now + "", ttl + "", expired + "", max + "");
        if (executeTimes != null) {
            if (executeTimes == 0) {
                log.error("【{}】在单位时间 {} 毫秒内已达到访问上限，当前接口上限 {}", key, ttl, max);
                return true;
            } else {
                log.info("【{}】在单位时间 {} 毫秒内访问 {} 次", key, ttl, executeTimes);
                return false;
            }
        }
        return false;
    }



}