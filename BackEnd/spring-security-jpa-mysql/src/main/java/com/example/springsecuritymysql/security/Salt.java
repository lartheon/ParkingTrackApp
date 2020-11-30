package com.example.springsecuritymysql.security;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Salt {

   String salt;// = BCrypt.gensalt().toString();

    public static String getSalt() {
        return "$2a$10$dCx.23.x1o3K09SnKO13WO";
//        setSalt("$2a$10$dCx.23.x1o3K09SnKO13WO");
//        return salt;
    }
}
