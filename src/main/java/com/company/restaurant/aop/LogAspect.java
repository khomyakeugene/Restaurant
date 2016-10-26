package com.company.restaurant.aop;

import com.company.util.common.Util;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;

/**
 * Modified by Yevhen on 25.10.2016.
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

    private ArrayDeque<Long> executionTimeDeque = new ArrayDeque<>();
    private Long lastMethodExecutionTime;

    @Before(RESOURCE_LOG_ALL_MASK)
    public void onBefore(JoinPoint joinPoint) throws Throwable {
        executionTimeDeque.push(Util.getNanoTime());
    }

    @After(RESOURCE_LOG_ALL_MASK)
    public void onAfter(JoinPoint joinPoint) throws Throwable {
        try {
            lastMethodExecutionTime = Util.getNanoTime() - executionTimeDeque.pop();
        } catch (NoSuchElementException e) {
            lastMethodExecutionTime = null;
        }
    }

    @AfterReturning(pointcut = RESOURCE_LOG_DEBUG_MASK, returning = "result")
    public void onAfterReturningDebug(JoinPoint joinPoint, Object result) throws Throwable {
        AOPLogger.debug(joinPoint, result, lastMethodExecutionTime);
    }

    @AfterReturning(pointcut = RESOURCE_LOG_INFO_MASK, returning = "result")
    public void onAfterReturningInfo(JoinPoint joinPoint, Object result) throws Throwable {
        AOPLogger.info(joinPoint, result, lastMethodExecutionTime);
    }

    @AfterThrowing(pointcut = RESOURCE_LOG_ALL_MASK, throwing = "throwable")
    public void onAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        AOPLogger.error(joinPoint, throwable, lastMethodExecutionTime);
    }
}
