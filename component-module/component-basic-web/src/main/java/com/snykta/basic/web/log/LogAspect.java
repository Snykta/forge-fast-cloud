package com.snykta.basic.web.log;


import cn.hutool.core.date.TimeInterval;
import cn.hutool.json.JSONUtil;
import com.snykta.tools.exception.ServiceException;
import com.snykta.basic.web.web.utils.IpUtil;
import com.snykta.tools.utils.CyDateUtil;
import com.snykta.tools.utils.CyObjUtil;
import com.snykta.tools.utils.CyStrUtil;
import com.snykta.tools.utils.CyExceptionUtil;
import com.snykta.tools.web.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * 控制台日志记录输出
 *
 * 注意：自定义业务 Controller 必须继承 BaseController 才会记录日志
 *
 */

@Component
@Aspect
@Slf4j
@Order(1)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})
public class LogAspect {


    private final Environment environment;

    public LogAspect(Environment environment) {
        this.environment = environment;
    }


    @Pointcut("target(com.snykta.basic.web.web.controller.BaseController)")
    public void inAction() {
    }



    @Around("inAction()")
    public Object doAction(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        boolean success = true;
        TimeInterval timerWatch = CyDateUtil.timer();
        StringBuilder sbLog = new StringBuilder();
        sbLog.append("\n\r------------操作日志开始------------------");
        LogDto logDto = new LogDto();
        Object returnValue = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

            String className = proceedingJoinPoint.getTarget().getClass().getName();
            logDto.setClassName(className);

            String methodName = proceedingJoinPoint.getSignature().getName();
            logDto.setMethodName(methodName);

            String appName = environment.getProperty("spring.application.name");
            logDto.setAppName(appName);

            List<Object> requestParamList = new ArrayList<>();
            Object[] args = proceedingJoinPoint.getArgs();
            for (Object object : args) {
                if (CyObjUtil.isNotNull(object) && (object instanceof Serializable)) {
                    requestParamList.add(object);
                }
            }
            String paramInfo = JSONUtil.toJsonStr(requestParamList);
            if (paramInfo.length() > 100000) {
                paramInfo = "长度超长已进行截断:" + paramInfo.substring(0, 100000);
            }
            sbLog.append("\n\r|--应用名称：").append(appName);
            sbLog.append("\n\r|--访问类名：").append(className);
            sbLog.append("\n\r|--访问方法：").append(methodName);
            sbLog.append("\n\r|--访问路径：").append(request.getRequestURI());
            sbLog.append("\n\r|--请求参数：").append(paramInfo);
            sbLog.append("\n\r|--请求方IP：").append(IpUtil.getIpAddr());


            // 开始执行程序
            returnValue = proceedingJoinPoint.proceed();

            sbLog.append("\n\r|--处理结果：成功");

        } catch (Exception e) {
            sbLog.append("\n\r|--处理结果：失败");
            sbLog.append("\n\r|--错误内容：")
                    .append(e)
                    .append("\n\r")
                    .append(CyExceptionUtil.getStackMsg(e));
            success = false;

            // 二次处理，重新抛出异常
            throw throwBindException(e);
        } finally {
            double totalSeconds = (double) (timerWatch.interval()) / 1000;
            sbLog.append("\n\r|--请求耗时：").append(String.format("%.2f", totalSeconds)).append("秒");
            sbLog.append("\n\r------------操作日志结束------------------");
            logDto.setTotalTime(totalSeconds);

            if (success) {
                log.info(sbLog.toString());
                log.info(logDto.toString());
            } else {
                log.error(sbLog.toString());
                log.error(logDto.toString());
            }
        }


        return returnValue;
    }




    /**
     * 自定义异常类
     * @param e
     * @return
     */
    private ServiceException throwBindException(Exception e) {
        String errorMsg = null;
        Integer resultCode = ResultCode.ERROR;
        if (e instanceof ServiceException) {
            errorMsg = e.getMessage();
            resultCode = ((ServiceException) e).getCode();
        } else {
            if (CyStrUtil.isNotEmpty(e.getMessage())) {
                errorMsg = e.getMessage();
            } else {
                if (CyObjUtil.isNotNull(e.getCause())) {
                    errorMsg = e.getCause().toString();
                }
            }
        }
        return new ServiceException(errorMsg, CyObjUtil.isNull(resultCode) ? ResultCode.ERROR : resultCode);
    }



}
