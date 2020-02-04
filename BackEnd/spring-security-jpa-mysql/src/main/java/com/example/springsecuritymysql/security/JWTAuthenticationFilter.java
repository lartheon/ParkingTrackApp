package com.example.springsecuritymysql.security;

import com.example.springsecuritymysql.model.Employee;
import com.example.springsecuritymysql.service.CustomUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.springsecuritymysql.security.SecurityConstants.*;

//import static com.auth0.jwt.algorithms.Algorithm.HMAC512;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        setFilterProcessesUrl(APP_API+LOGIN_URL);
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            Employee creds = new ObjectMapper()
                    .readValue(req.getInputStream(), Employee.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain, Authentication authentication) throws IOException {

        CustomUserDetails authUser = ((CustomUserDetails) authentication.getPrincipal());
//        UserDetails user = customUserDetailsService.loadUserByUsername(authUser.getEmail());


        List<String> roles = authUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setSubject(authUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claim("role", roles)
                .compact();

        response.addHeader(HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        String json = gson.toJson(authUser);
        response.getWriter().write(json);

//        System.out.println("JWT TOKEN: " + token +"\nJSON "+json);
    }

}