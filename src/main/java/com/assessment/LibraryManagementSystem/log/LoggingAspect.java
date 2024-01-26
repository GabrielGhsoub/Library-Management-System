package com.assessment.LibraryManagementSystem.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Before("within(@org.springframework.stereotype.Controller *) || within(@org.springframework.stereotype.Service *)")
	public void logBefore(JoinPoint joinPoint) {
		log.info("Entering: " + joinPoint.getSignature().toShortString());
	}

	@AfterReturning(pointcut = "within(@org.springframework.stereotype.Controller *) || within(@org.springframework.stereotype.Service *)", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		log.info("Exiting: " + joinPoint.getSignature().toShortString() + " with result = " + result);
	}

	@AfterThrowing(pointcut = "within(@org.springframework.stereotype.Controller *) || within(@org.springframework.stereotype.Service *)", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		log.error("Exception in " + joinPoint.getSignature().toShortString() + " with message = " + error.getMessage());
	}
}
