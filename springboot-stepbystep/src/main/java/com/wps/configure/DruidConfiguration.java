package com.wps.configure;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Druid数据源监控
 */
@Configuration
public class DruidConfiguration {

    @Bean
    public ServletRegistrationBean druidStatViewServlet(){
        //ServeltRegistrationBean提供类进行注册
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //添加初始化参数： initParams

        //白名单
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");

        //IP 黑名单（存在共同 时， deny 优先于 allow)
        //如果满足 deny，就提示： Sorry, you are not permitted to view t his page .
       // servletRegistrationBean.addInitParameter("deny","192.168.3.108");

        //登录查看信息的账号和密码
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","123456");

        //是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable","true");

        return servletRegistrationBean;
    }


    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.css,*.ico,/drudi/*");
        return filterRegistrationBean;
    }


}
