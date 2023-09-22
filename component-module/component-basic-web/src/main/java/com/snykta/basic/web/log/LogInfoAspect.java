package com.snykta.basic.web.log;


import cn.hutool.json.JSONUtil;
import com.snykta.basic.web.exception.ServiceException;
import com.snykta.basic.web.utils.FastExceptionUtils;
import com.snykta.basic.web.utils.FastObjUtil;
import com.snykta.basic.web.utils.FastStrUtil;
import com.snykta.basic.web.web.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 控制台日志记录输出
 */

@Component
@Aspect
@Slf4j
@Order(1)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})
public class LogInfoAspect {


    private final Environment environment;

    public LogInfoAspect(Environment environment) {
        this.environment = environment;
    }


    @Pointcut("execution(public * com.fast.start.*.controller.*Controller.*(..))")
    public void inAction() {
    }



    @Around("inAction()")
    public Object doAction(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        StringBuilder sbLog = new StringBuilder();
        sbLog.append("\n\r------------操作日志开始------------------");
        Object returnValue = null;
        boolean success = true;
        LogInfoDto logInfoDto = new LogInfoDto();
        try {
            stopWatch.start();
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            assert attributes != null;
            HttpServletRequest request = attributes.getRequest();
            //1获得调用的类
            String className = proceedingJoinPoint.getTarget().getClass().getName();
            logInfoDto.setClassName(className);
            //获得调用的方法
            String methodName = proceedingJoinPoint.getSignature().getName();
            logInfoDto.setMethodName(methodName);
            //获得参数
            List<Object> canSerizableList = new ArrayList<>();
            Object[] args = proceedingJoinPoint.getArgs();
            for (Object object : args) {
                if (FastObjUtil.isNotNull(object) && (object instanceof Serializable)) {
                    canSerizableList.add(object);
                }
            }
            String paramInfo = JSONUtil.toJsonStr(canSerizableList);
            if (paramInfo.length() > 100000) {
                paramInfo = "长度超长已进行截断:" + paramInfo.substring(0, 100000);
            }
            String appName = environment.getProperty("spring.application.name");
            sbLog.append("\n\r|--应用名称：" + appName);
            logInfoDto.setAppName(appName);
            sbLog.append("\n\r|--访问类名：" + className);
            sbLog.append("\n\r|--访问方法：" + methodName);
            sbLog.append("\n\r|--访问路径：" + request.getRequestURI());
            try {
                sbLog.append("\n\r|--请求参数：" + paramInfo);
            } catch (Exception e) {
                sbLog.append("\n\r|--请求参数：解析异常错误原因->" + e.getMessage());
            }
            returnValue = proceedingJoinPoint.proceed();
            sbLog.append("\n\r|--处理结果：正常");

        } catch (Exception e) {
            sbLog.append("\n\r|--处理结果：失败");
            String errorMsg = null;

            if (e instanceof ServiceException) {
                errorMsg = e.getMessage();
            } else {
                if (FastStrUtil.isNotEmpty(e.getMessage())) {
                    errorMsg = e.getMessage();
                } else {
                    if (FastObjUtil.isNotNull(e.getCause())) {
                        errorMsg = e.getCause().toString();
                    }
                }
            }
            sbLog.append("\n\r|--错误内容：" + e + "\n\r" + FastExceptionUtils.getStackMsg(e));
            success = false;
            throw new ServiceException(errorMsg, ResultCode.ERROR);
        } finally {
            stopWatch.stop();
            Double totalTimeSeconds = stopWatch.getTotalTimeSeconds();
            sbLog.append("\n\r|--请求耗时：" + String.format("%.2f", totalTimeSeconds) + " 秒");
            sbLog.append("\n\r------------操作日志结束------------------");
            //设置统计时间
            logInfoDto.setTotalTime(totalTimeSeconds);
            if (success) {
                log.info(sbLog.toString());
                //加入耗时统计的日志
                log.info(logInfoDto.toString());
            } else {
                log.error(sbLog.toString());
                log.error(logInfoDto.toString());
            }
        }

        return returnValue;
    }



}
