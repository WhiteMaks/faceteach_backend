package ru.faceteach.microservice.configs;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import ru.faceteach.microservice.domains.User;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
public class JwtUtil {
    private final String secretKey = "asd2edasdasdasdasdasdasdasdasdasd21123123123123-12312-312312"; //todo вынести в переменные окружения
    private final String expirationTime = "86400"; //todo вынести в переменные окружения

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes())).build().parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token) {
        return getClaims(token).getExpiration().after(new Date());
    }

    public String generateToken(User user) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", List.of(user.getRole()));
        long seconds = Long.parseLong(expirationTime);
        Date created = new Date();
        Date expiration = new Date(created.getTime() + seconds * 1000);
        return Jwts.builder().setClaims(claims).setSubject(user.getUsername()).setIssuedAt(created).setExpiration(expiration).signWith(Keys.hmacShaKeyFor(secretKey.getBytes())).compact();
    }
}
