package com.example.springsecuritymysql.config;

import com.example.springsecuritymysql.security.SpringSecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer
        extends AbstractSecurityWebApplicationInitializer {

protected  Class<?>[] getRootConfigClasses(){
    return new Class[] {SpringSecurityConfig.class};
}
    SecurityWebApplicationInitializer(){
        System.out.println("SecurityWebApplicationInitializer active");
    }
}