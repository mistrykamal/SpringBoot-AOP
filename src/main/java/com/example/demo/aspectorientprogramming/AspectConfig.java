package com.example.demo.aspectorientprogramming;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class AspectConfig {
		
	private static final Logger log = LoggerFactory.getLogger(AspectConfig.class);
	 
	/**
	 * 	  *.*  for any class with any method 
	 * 	  (..) for any number of argument
	 */
	@Pointcut(value = "execution(* com.example.demo.controller.*.*(..))")
	public void myPointcutAdvice() {
		
	}
	
	@Around("myPointcutAdvice()")
	public Object applicationLogger(ProceedingJoinPoint joinpoint) throws Throwable {
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
	
}
