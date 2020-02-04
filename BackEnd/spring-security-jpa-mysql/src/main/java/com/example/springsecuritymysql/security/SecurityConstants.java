package com.example.springsecuritymysql.security;

public class SecurityConstants {

    public static final String SECRET = ".^|I$U>v>WD+f>tnl%A9Hfw$Ub=p6@,P`kw;0Jg=I[s^z~Yd5-2h/0!9iswk@ONP";//Salt.getSalt();
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";
    public static final String APP_API = "/api";
    public static final String LOGIN_URL = "/employeesLogin/";
    public static final String SIGN_OUT_URL = "/signOut";
    public static final String SIGN_UP_URL = "/employeesForm/";
    public static final String EMPLOYEES_URL = "/employees";
    public static final String VEHICLES_URL = "/vehicles";
}
