package com.epam.cdp.springbootlab.logging;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Log4j2
@Component
public class LogAspect {

    @Around("@annotation(com.epam.cdp.springbootlab.logging.Log)")
    public Object myAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long startTime = System.nanoTime();
        log.info("started method: " + proceedingJoinPoint.getSignature() + "; " + Arrays.toString(proceedingJoinPoint.getArgs()));
        Object value = proceedingJoinPoint.proceed();

        long endTime = System.nanoTime();
        log.info("executed method: " + proceedingJoinPoint.getSignature() + "; in " + (endTime - startTime) / 1000000 + "ms");

        return value;

    }

}
