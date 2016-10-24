package com.company.restaurant.aop;

import com.company.util.common.Util;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;

/**
 * Created by Yevhen on 24.10.2016.
 */

public class AOPLogger {
    private static final String MESSAGE_EXECUTION_NANO_TIME_PATTERN = "%s (execution time: %d micro seconds)";
    private static Logger logger;

    private static void initLogger() {
        logger = Logger.getLogger(Util.getApplicationMainClass());
    }

    private static Logger getLogger() {
        if (logger == null) {
            initLogger();
        }

        return logger;
    }

    public static String methodFullName(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();

        return String.format("%s.%s", signature.getDeclaringTypeName(),
                signature.getName());
    }

    private static String methodParameters(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();

        String result = "";
        for (int i = 0; i < parameterNames.length; i++) {
            if (result.length() > 0) {
                result = result + ", ";
            }
            result = String.format("%s%s = %s", result, parameterNames[i], Util.toString(parameterValues[i]));
        }

        return result;
    }

    private static String methodResultRepresentation(JoinPoint joinPoint, Object methodResult) {
        String result = null;

        Class returnType = ((MethodSignature)joinPoint.getSignature()).getReturnType();
        if (returnType != Void.TYPE) {
            // Partly convert some types "toString"
            if (returnType.isArray()) {
                result = Arrays.toString((Object[]) methodResult);
            } else {
                // All "another" types
                result = Util.toString(methodResult);
            }
        }

        return result;
    }

    private static String logMessage(JoinPoint joinPoint) {
        return String.format("%s(%s)", methodFullName(joinPoint), methodParameters(joinPoint));
    }

    private static String logMessage(JoinPoint joinPoint, Object result) {
        return (result == null) ? logMessage(joinPoint) :
                String.format("%s = %s", logMessage(joinPoint), methodResultRepresentation(joinPoint, result));
    }

    private static String logMessageWithExecutionTime(String logMessage, Long executionNanoTime) {
        return (executionNanoTime == null) ? logMessage : String.format(MESSAGE_EXECUTION_NANO_TIME_PATTERN, logMessage,
                Util.nanoToMicroTime(executionNanoTime));
    }

    private static String logMessage(JoinPoint joinPoint, Object result, Long executionNanoTime) {
        return logMessageWithExecutionTime(logMessage(joinPoint, result), executionNanoTime);
    }

    private static String logMessage(JoinPoint joinPoint, Long executionNanoTime) {
        return logMessageWithExecutionTime(logMessage(joinPoint), executionNanoTime);
    }

    public static void debug(JoinPoint joinPoint, Object result, Long executionNanoTime) {
        getLogger().debug(logMessage(joinPoint, result, executionNanoTime));
    }

    public static void info(JoinPoint joinPoint, Object result, Long executionNanoTime) {
        getLogger().info(logMessage(joinPoint, result, executionNanoTime));
    }

    public static void error(JoinPoint joinPoint, Throwable throwable, Long executionNanoTime) {
        getLogger().error(logMessage(joinPoint, executionNanoTime), throwable);
    }
}
