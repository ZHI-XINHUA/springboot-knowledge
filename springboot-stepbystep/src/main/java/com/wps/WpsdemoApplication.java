package com.wps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.wps.mybatis.dao") //扫描mybatis dao接口
@ServletComponentScan //使用此注解后，Servlet、Filter、Listener可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册
@EnableAsync //开启异步
public class WpsdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WpsdemoApplication.class, args);
	}

}

