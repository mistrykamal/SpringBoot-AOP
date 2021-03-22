package com.example.demo.aspectorientprogramming;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Configuration
public class AspectConfig {
		
	private static final Logger log = LoggerFactory.getLogger(AspectConfig.class);
	 
	/**
	 * 	  *.*  for any class with any method 
	 * 	  (..) for any number of argument
	 */
	@Pointcut(value = "execution(* com.example.demo.controller.*.*(..))")
	public void myPointcutAdviceForPackage() {
		
	}
	
	@Pointcut(value = "within(@org.springframework.web.bind.annotation.RestController *)")
	public void myPointcutAdviceForBean() {
		
	}
	
	@Around("myPointcutAdviceForPackage() && myPointcutAdviceForBean()")
	public Object applicationLoggerAdvice(ProceedingJoinPoint joinpoint) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		
		String methodName = joinpoint.getSignature().getName();
		String className = joinpoint.getTarget().getClass().getName();
		Object[] arguements = joinpoint.getArgs();
		
		log.info("method invoked " + methodName + "() from " + className);
		log.info("method arguments:  " + mapper.writeValueAsString(arguements));
		Object object = joinpoint.proceed();
		log.info(methodName + "() from " + className);
		log.info("Response: " + mapper.writeValueAsString(object));
		
		return object;
		
	}
	
	@Pointcut("within(com.example.demo.*.*)")
	public void myPointcutExceptionPackage() {
		
	}
	
	@AfterThrowing(pointcut = "myPointcutExceptionPackage()", throwing = "exception")
	public void exceptionLoggerAdvice(JoinPoint joinpoint, Throwable exception) {
		
		log.error("Exception thrown in {}.{}  with cause = {},  with message = {} ", 
					joinpoint.getSignature().getDeclaringTypeName(), 
					joinpoint.getSignature().getName(), 
					exception.getCause() != null ? exception.getCause() : "NULL", 
					exception.getMessage() != null ? exception.getMessage() : "NULL"
					);
	}
	
}
