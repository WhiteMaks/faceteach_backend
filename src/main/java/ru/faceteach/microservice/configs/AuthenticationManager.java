package ru.faceteach.microservice.configs;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationManager(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        String username;
        try {
            username =  jwtUtil.extractUsername(token);
        } catch (Exception e) {
            username = null;
        }
        if (username != null && jwtUtil.validateToken(token)) {
            Claims claims = jwtUtil.getClaims(token);
            List<String> role = claims.get("role", List.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, role.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
            return Mono.just(authenticationToken);
        } else {
            return Mono.empty();
        }
    }
}
