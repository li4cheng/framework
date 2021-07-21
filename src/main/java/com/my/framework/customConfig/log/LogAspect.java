package com.my.framework.customConfig.log;

import com.alibaba.fastjson.JSONObject;
import com.my.framework.config.ApplicationProperties;
import com.my.framework.customConfig.log.table.OperationLogService;
import io.swagger.annotations.Api;
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
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LogAspect {

    private final Logger log = LoggerFactory.getLogger(LogAspect.class);

    private final ApplicationProperties applicationProperties;
    private final OperationLogService operationLogService;

    public LogAspect(ApplicationProperties applicationProperties, OperationLogService operationLogService) {
        this.applicationProperties = applicationProperties;
        this.operationLogService = operationLogService;
    }

    @Pointcut("@annotation(com.my.framework.customConfig.log.Log)")
    private void aspect() {
    }

    @Around("aspect()")
    public Object aroundAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Api api = AnnotationUtils.findAnnotation(joinPoint.getSignature().getDeclaringType(), Api.class);
        String[] tags = api == null ? "".split("") : api.tags();//类api注解值
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        ApiOperation apiOperation = targetMethod.getAnnotation(ApiOperation.class);
        if (applicationProperties.getShowLog()) {
            log.info("==============start==============");
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
        }

        return joinPoint.proceed();
    }

    @AfterReturning(value = "aspect()", returning = "returnValue")
    public void afterReturnAspect(JoinPoint joinPoint, Object returnValue) {
        //获取传参
        Object[] args = joinPoint.getArgs();
        String[] paramNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Map<String, Object> paramMap = new HashMap<>();
        for (int i = 0; i < paramNames.length; i++) {
            paramMap.put(paramNames[i], args[i]);
        }
        JSONObject paramJson = JSONObject.parseObject(JSONObject.toJSONString(paramMap), JSONObject.class);
        String paramJsonString = JSONObject.toJSONString(paramJson);

        //获取返回值
        String returnValueString = returnValue.toString();

        //获取标签
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        ApiOperation apiOperation = targetMethod.getAnnotation(ApiOperation.class);
        Log logAop = targetMethod.getAnnotation(Log.class);
        LogTag tag = logAop.tag();

        operationLogService.saveOperationLog(apiOperation == null ? "" : apiOperation.value(), paramJsonString, returnValueString, tag);
        if (applicationProperties.getShowLog()) {
            log.info("{}[{}] 输出值: {", apiOperation == null ? "" : apiOperation.value(), joinPoint.getSignature().getName());
            log.info("  {}", returnValue);
            log.info("}");
            log.info("===============end===============");
        }
    }
}
