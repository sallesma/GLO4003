package com.glo4003.project.global;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

public class ContextProvider implements ApplicationContextAware {

    private static ApplicationContext ctx;


    @Override
    public void setApplicationContext(ApplicationContext appContext)
            throws BeansException {
        ctx = appContext;

    }

    public static Map<String, Object> getApplicationControllers() {
        return ctx.getBeansWithAnnotation(Controller.class);
    }
}