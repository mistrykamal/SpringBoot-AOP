package com.example.demo.aspectorientprogramming;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AspectConfig {
	
	@Before("execution(public void createEmployee(..))")
	public void createTriggered() {
		System.out.println("Create method is triggered");
	}
	
	@After("execution(public void createEmployee(..))")
	public void after(JoinPoint joinPoint) {
		System.out.println(joinPoint.getSignature().getName() + " method is Done executing");
	}
}
