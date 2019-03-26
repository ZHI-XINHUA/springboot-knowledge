package com.wps.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class WebSecurityConf extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /* http.formLogin()
                .failureUrl("/login?error")
                .defaultSuccessUrl("/thymeleaf/user/zs")
                .permitAll();
        super.configure(http);*/

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .passwordEncoder(new MyPasswordEncoder())
                .withUser("zxh").password("123456").roles("ADMIN")
                .and()
                .withUser("james").password("123").roles("USERS");
    }
}
