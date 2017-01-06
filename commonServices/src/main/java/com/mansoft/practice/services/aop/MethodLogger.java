package com.mansoft.practice.services.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component
public class MethodLogger {
	
	Logger logger=LoggerFactory.getLogger(MethodLogger.class);

	
	@Around("execution(* com.mansoft.practice.services..*.*(..))")
	public Object timeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		Object retVal = joinPoint.proceed();

		stopWatch.stop();

		StringBuffer logMessageStringBuffer = new StringBuffer();
		logMessageStringBuffer.append(joinPoint.getTarget().getClass().getName());
		logMessageStringBuffer.append(".");
		logMessageStringBuffer.append(joinPoint.getSignature().getName());
		logMessageStringBuffer.append("(");
		logMessageStringBuffer.append(joinPoint.getArgs());
		logMessageStringBuffer.append(")");
		logMessageStringBuffer.append(" execution time: ");
		logMessageStringBuffer.append(stopWatch.getTotalTimeMillis());
		logMessageStringBuffer.append(" ms");

		logger.info(logMessageStringBuffer.toString());

		return retVal;
	}

}