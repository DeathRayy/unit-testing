package com.base.aop;

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
public class LoggerAdvice {

	private static final Logger log = LoggerFactory.getLogger(LoggerAdvice.class);
	
	@Pointcut(value = "execution(* com.base.*.*.*(..) )")
	public void pointCut() {
		
	}
	
	@Around("pointCut()")
	public Object logggerConfig(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String className= pjp.getTarget().getClass().getName();
		String mehtodName = pjp.getSignature().getName();
		Object[] args = pjp.getArgs();
		// When entering into method
		log.info("Entered into class "+className+" method ()"+mehtodName +" with arguments "+mapper.writeValueAsString(args));
		
		Object proceed = pjp.proceed();
		
		// While leaving method
		log.info("Leaving  class "+className+" method ()"+mehtodName +" with response "+mapper.writeValueAsString(proceed));
		
		return proceed;
	}
	
}
