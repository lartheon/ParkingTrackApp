//package com.example.springsecuritymysql.security;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//
//import java.util.Date;
//
//import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
//
//@Component
//public class JwtTokenProvider {
//    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
//
//    @Value("${app.jwtSecret}")
//    private String jwtSecret;
//
//    @Value("${app.jwtExpirationInMs}")
//    private int jwtExpirationInMs;
//
//    public String generateToken(Authentication authentication){
//        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
//
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
//
////        return  authentication.getCredentials().toString();
//        String token = JWT.create()
//                .withSubject(((CustomUserDetails) authentication.getPrincipal()).getEmail())
//                .withExpiresAt(expiryDate)
//                .sign(HMAC512(jwtSecret.getBytes()));
////        res.addHeader("Authorization", "Bearer " + token);
//
//        return  token;
//    }
//
//    public Long getUserIdFromJWT(String token) {
////        Claims claims = Jwts.parser()
////                .setSigningKey(jwtSecret)
////                .parseClaimsJws(token)
////                .getBody();
////
////        return Long.parseLong(claims.getSubject());
//        return null;
//    }
//
//    public boolean validateToken(String authToken) {
//        try {
////            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
//            return true;
//        }
////        catch (SignatureException ex) {
////            logger.error("Invalid JWT signature");
////        }
////        catch (MalformedJwtException ex) {
////            logger.error("Invalid JWT token");
////        } catch (ExpiredJwtException ex) {
////            logger.error("Expired JWT token");
////        } catch (UnsupportedJwtException ex) {
////            logger.error("Unsupported JWT token");
////        }
//        catch (IllegalArgumentException ex) {
//            logger.error("JWT claims string is empty.");
//        }
//        return false;
//    }
//}
