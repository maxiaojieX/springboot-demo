package com.danger.testOther;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by xiaojie.Ma on 2018/9/3.
 */
@Component
public class ListenerContext implements ServletContextListener,ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("容器开始初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("contextDestroyed");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("容器初始化完成");
    }
}
