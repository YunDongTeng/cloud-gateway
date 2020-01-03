package com.cloud.gateway.util;

import org.springframework.context.ApplicationContext;

public class ApplicationContextUtil {

    private static ApplicationContext applicationContext;


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class clazz) {
        return (T) ApplicationContextUtil.getApplicationContext().getBean(clazz);
    }

    public static void main(String[] args){
        System.out.println("/api/order/list?source=area".substring(0,"/api/order/list?source=area".indexOf("?")));
    }
}
