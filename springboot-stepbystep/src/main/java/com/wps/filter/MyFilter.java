package com.wps.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 自定义过滤器
 * @WebFilte:
 * 用于将一个类声明为过滤器，该注解将会在应用部署时被容器处
 * 理，容器根据具体的属性配直将相应的类部署为过滤器 。 这样我们在 Web 应
 * 用中使用 监听器时，不需要在 web. xml 文件中配直监听器的相关描述信息 。
 * 该注解的常用属性有 filterName 、 urlPatterns 、 value 等 。 filterName 属性用于指
 * 定过滤器的 name，等价于 XML 配直文件中的 ＜自lter-name＞标签。 urlPattems
 * 属性用于指定一组过滤器的 URL 匹配模式，等价于 XML 配直文件 中 的＜urlpa忧em＞标签。 va lue 属性等价于 ur!Pattems 属性，但是两者不可以同时使用
 */
@WebFilter(filterName = "myFilter",urlPatterns = "/*")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("==================myfilter>init===============");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("==================myfilter>doFilter===============");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("==================myfilter>destory===============");
    }
}
