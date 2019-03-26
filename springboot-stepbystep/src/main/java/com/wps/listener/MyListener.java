package com.wps.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * ServletContextListener 类：能够监听 ServletContext 对 象 的生命周 期， 实际上
 * 就是监听 Web 应用的生命周 期 。 当 Servlet 容器启动或终止 Web 应用时，会
 * 触友 ServletContextEvent 事件，该事件由 ServletContextListener 类来处理 。
 */
@WebListener
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("===========ServletContext上下文初始化================");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("===========ServletContext上下文销毁================");
    }
}
