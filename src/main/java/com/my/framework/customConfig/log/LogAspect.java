package com.my.framework.customConfig.log;

import com.my.framework.service.OperationLogService;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {

    private final Logger log = LoggerFactory.getLogger(LogAspect.class);

    private final OperationLogService operationLogService;

    public LogAspect(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    @Pointcut("@annotation(com.my.framework.customConfig.log.Log)")
    private void aspect() {
    }

    @Around("aspect()")
    public Object aroundAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("==============start==============");
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        ApiOperation apiOperation = targetMethod.getAnnotation(ApiOperation.class);
        log.info("{}[{}] 输入参数: {", apiOperation == null ? "" : apiOperation.value(), joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        String[] paramNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (paramNames.length == 0) {
            log.info("  无参数");
        } else {
            for (int i = 1; i <= paramNames.length; i++) {
                log.info("  {} = {}", paramNames[i - 1], args[i - 1]);
            }
        }
        log.info("}");

        return joinPoint.proceed();
    }

    @AfterReturning(value = "aspect()", returning = "returnValue")
    public void afterReturnAspect(JoinPoint joinPoint, Object returnValue) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        ApiOperation apiOperation = targetMethod.getAnnotation(ApiOperation.class);
        //保存操作日志
        operationLogService.saveOperationLog(apiOperation == null ? "" : apiOperation.value());
        log.info("{}[{}] 输出值: {", apiOperation == null ? "" : apiOperation.value(), joinPoint.getSignature().getName());
        log.info("  {}", returnValue);
        log.info("}");
        log.info("===============end===============");
    }
}
