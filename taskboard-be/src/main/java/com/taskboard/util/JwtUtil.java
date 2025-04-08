package com.taskboard.util;

import java.util.Date;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
    private static final String SECRET_KEY = "abcdefghijklmnopqrstuvwxyz123456"; // 32자 이상 되어야 사용 가능
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) //sub   ex). sub (Subject): "abc@aaa.com" → 사용자 이메일 (혹은 ID)
                .setIssuedAt(new Date()) //iat  ex). iat (Issued At): 1742290284 → 토큰 발급시간
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //exp  ex). exp (Expiration): 1742293884 → 토큰 만료시간
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 서명 Signature
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
