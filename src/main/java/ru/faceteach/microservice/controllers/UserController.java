package ru.faceteach.microservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import ru.faceteach.microservice.configs.JwtUtil;
import ru.faceteach.microservice.domains.User;
import ru.faceteach.microservice.services.UserService;

import java.security.Principal;
import java.util.Objects;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final static ResponseEntity<Object> UNAUTHORIZED = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("login")
    public Mono<ResponseEntity> login(ServerWebExchange serverWebExchange) {
        return serverWebExchange.getFormData().flatMap(credentials ->
                userService.findByUsername(credentials.getFirst("username")).cast(User.class)
                        .map(user -> Objects.equals(credentials.getFirst("password"), user.getPassword()) ? ResponseEntity.ok(jwtUtil.generateToken(user)) : UNAUTHORIZED)
                        .defaultIfEmpty(UNAUTHORIZED));
    }

    @GetMapping
    public Mono<Boolean> isAuth(Principal principal) {
        if (principal == null) {
            return Mono.just(false);
        }
        return Mono.just(((UsernamePasswordAuthenticationToken) principal).isAuthenticated());
    }
}
