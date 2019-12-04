/*
package com.example.springsecuritymysql.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


PK: todo add security

    // Create 2 users for demo
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");

    }

    // Secure the endpoints with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/employees/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/employees").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/emplyees/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/employees/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/employees/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }



}
*/