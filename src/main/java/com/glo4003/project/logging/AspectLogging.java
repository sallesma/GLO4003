package com.glo4003.project.logging;

import java.util.Map;

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
        fileAppender.setFile("src/main/java/com/glo4003/project/logging/" + fileName);
        fileAppender.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
        fileAppender.setThreshold(Priority.INFO);
        fileAppender.setAppend(true);
        fileAppender.activateOptions();

        logger = Logger.getLogger(clazz);
        logger.addAppender(fileAppender);
	}
    protected void logInfo(JoinPoint joinPoint, Map<String, String[]> args) {
        final String logInfo = joinPoint.getTarget().getClass()
                + "." + joinPoint.getSignature().getName() + " " + args;
        
        logger.log(Priority.INFO, logInfo);
    }
}
