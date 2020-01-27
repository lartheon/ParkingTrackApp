//package com.example.springsecuritymysql.config;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
////@Configuration
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//
//        http.cors()
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
//    }
//}
//
//
//
package com.example.springsecuritymysql.config;

import com.example.springsecuritymysql.repository.EmployeeRepository;
import com.example.springsecuritymysql.service.CustomUserDetailsService;
import com.example.springsecuritymysql.service.Salt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = EmployeeRepository.class)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    public SpringSecurityConfig(){
        super();
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;

//    @Autowired
//    private EmployeeController employeeController;


    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.authenticationProvider(authenticationProvider());

//        auth.userDetailsService(userDetailsService)
//        .passwordEncoder(getPasswordEncoder());
       /* auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");*/
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(getPasswordEncoder());
        return authProvider;
    }
    // Secure the endpoints with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .formLogin()
                .loginPage("/employeesLogin")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/**")/*.authenticated()*/.permitAll()
                .antMatchers(HttpMethod.POST, "/employeesLogin").permitAll()
//                .antMatchers("/admin").hasRole("ADMIN")
//                .antMatchers("/EmployeesForm/*").hasAnyRole("ADMIN", "USER")
                .and()
                .csrf().disable().cors().disable()
//                .anonymous().disable()


       /*
                .exceptionHandling()
//                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .authenticationEntryPoint(new AuthenticationEntryPoint(){ //<< implementing this interface
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response,
                                 AuthenticationException authException) throws IOException, ServletException {
                //>>> response.addHeader("WWW-Authenticate", "Basic realm=\"" + realmName + "\""); <<< (((REMOVED)))
                response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
            }
        })*/


//        .and()
//                .csrf().disable().cors().disable()
//        ;
//        http
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .and()
//                .formLogin().loginPage("/employeesLogin").permitAll()
//                .and()
//                .logout().permitAll()

//                .antMatchers("/","/employees").permitAll()
//                .antMatchers("/employees/**").authenticated()
//                .anyRequest().hasAnyRole("USER", "ADMIN")
//                .antMatchers("/EmployeesForm").authenticated()
//                .anyRequest().permitAll()
//                .antMatchers("/employeesForm/**")
////                .anyRequest()
//                .hasAnyRole("USER", "ADMIN")
//                .antMatchers(HttpMethod.POST, "/employeesLogin").permitAll()
//                .and()
//                .formLogin().loginPage("/employeesLogin").permitAll()
//                .and()
//                .logout().permitAll()
//                .antMatchers(HttpMethod.GET, "/employees/**").hasAnyRole("USER", "ADMIN")
//                .antMatchers(HttpMethod.POST, "/employees").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/employees/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PATCH, "/employees/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/employees/**").hasRole("ADMIN")
//                .and()
//
//                .formLogin().disable();
        ;
    }
    // @Bean
// public PasswordEncoder passwordEncoder() {
//     return new BCryptPasswordEncoder();
// }
    @Autowired
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    @Autowired
    public static PasswordEncoder getPasswordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
//                return charSequence.toString();
//             Salt salt = new Salt();
//             System.out.println("hash: " + salt.getSalt());
                String hashed = BCrypt.hashpw(charSequence.toString(), Salt.getSalt());
                return hashed;
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
//             return true;
                return encode(charSequence.toString()).equals(s);
            }
        };
    }


}
