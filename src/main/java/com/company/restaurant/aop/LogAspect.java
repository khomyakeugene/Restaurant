package com.company.restaurant.aop;

import com.company.util.common.Util;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.HashMap;

/**
 * Created by Yevhen on 24.10.2016.
 */

@Aspect
public class LogAspect {
    private static final String RESOURCE_LOG_EXCLUDE_MASK =
            "!(execution(* get*(..)) || execution(* set*(..)))";
    private static final String RESOURCE_LOG_SERVICE_MASK =
            "(execution (* com.company.restaurant.service..*(..)))";
    private static final String RESOURCE_LOG_WEB_MASK =
            "(execution (* com.company.restaurant.web..*(..)))";
    private static final String RESOURCE_LOG_ALL_MASK =
            "(execution (* com.company..*(..)))" + " && " + RESOURCE_LOG_EXCLUDE_MASK;

    private static final String RESOURCE_LOG_INFO_MASK =
            "(" + RESOURCE_LOG_SERVICE_MASK + " || " + RESOURCE_LOG_WEB_MASK + ") && " + RESOURCE_LOG_EXCLUDE_MASK;
    private static final String RESOURCE_LOG_DEBUG_MASK = RESOURCE_LOG_ALL_MASK + "&& !(" + RESOURCE_LOG_INFO_MASK + ")";

    private HashMap<String, Long> executionTimeMap = new HashMap<>();

    private String methodFullName(JoinPoint joinPoint) {
        return AOPLogger.methodFullName(joinPoint);
    }

    private Long methodExecutionTime(JoinPoint joinPoint) {
        return executionTimeMap.get(methodFullName(joinPoint));
    }

    @Before(RESOURCE_LOG_ALL_MASK)
    public void onBefore(JoinPoint joinPoint) throws Throwable {
        // Calculate all temporary data beforehand of <before time> fixing because of needful precision of <before time>
        String methodFullName = methodFullName(joinPoint);
        // Store <before time>
        executionTimeMap.put(methodFullName, Util.getNanoTime());
    }

    @After(RESOURCE_LOG_ALL_MASK)
    public void onAfter(JoinPoint joinPoint) throws Throwable {
        // Fix <after time> (before of all other calculation)
        Long afterTime = Util.getNanoTime();
        // Get <full method name>
        String methodFullName = methodFullName(joinPoint);
        // Get <before time>
        Long beforeTime = executionTimeMap.get(methodFullName);
        // Store execution time for this method
        if (beforeTime != null) {
            executionTimeMap.put(methodFullName, afterTime - beforeTime);
        }
    }

    @AfterReturning(pointcut = RESOURCE_LOG_DEBUG_MASK, returning = "result")
    public void onAfterReturningDebug(JoinPoint joinPoint, Object result) throws Throwable {
        AOPLogger.debug(joinPoint, result, methodExecutionTime(joinPoint));
    }

    @AfterReturning(pointcut = RESOURCE_LOG_INFO_MASK, returning = "result")
    public void onAfterReturningInfo(JoinPoint joinPoint, Object result) throws Throwable {
        AOPLogger.info(joinPoint, result, methodExecutionTime(joinPoint));
    }

    @AfterThrowing(pointcut = RESOURCE_LOG_ALL_MASK, throwing = "throwable")
    public void onAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        AOPLogger.error(joinPoint, throwable, methodExecutionTime(joinPoint));
    }
}
