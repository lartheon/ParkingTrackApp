package com.example.springsecuritymysql.security;

import com.example.springsecuritymysql.exception.UserAlreadyExistAuthenticationException;
import com.example.springsecuritymysql.model.Employee;
import com.example.springsecuritymysql.service.CustomUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
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
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.springsecuritymysql.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private UserAlreadyExistAuthenticationException userAlreadyExistAuthenticationException;

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
        String role = roles.get(0).replaceAll("^\"|\"$",roles.get(0)); //somehow between here and the client role string gets wrapped in quotes, why??
        CustomUserDetails newAuthUser = new CustomUserDetails(
                authUser.getId(),
                null,
                null,
                null,
                authUser.getEmail(),
                null,
                null,
                null,
                role
        );
//        String jsonRole = gson.toJson(roleCollection.iterator().next());
        String jsonUser = gson.toJson(newAuthUser);
//        String json = gson.toJson(jsonUser +','+ jsonRole);
        response.getWriter().write(jsonUser);

        System.out.println("JWT TOKEN: " + token +"\nJSON "+jsonUser);
    }

}
