package com.glo4003.project.logging;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.Priority;
import org.aspectj.lang.JoinPoint;

public class AspectLogging {
	
	private Logger logger;
	
	public AspectLogging(String fileName, Class clazz) {
		FileAppender fileAppender = new FileAppender();
        fileAppender.setName("FileLogger");
        fileAppender.setFile("src/main/java/com/glo4003/project/aspect/" + fileName);
        fileAppender.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
        fileAppender.setThreshold(Priority.INFO);
        fileAppender.setAppend(true);
        fileAppender.activateOptions();

        logger = Logger.getLogger(clazz);
        logger.addAppender(fileAppender);
	}
    protected void LogName(JoinPoint joinPoint) {
        final String methodName = joinPoint.getTarget().getClass()
                + "." + joinPoint.getSignature().getName();
        
        logger.log(Priority.INFO, methodName);
    }
}
