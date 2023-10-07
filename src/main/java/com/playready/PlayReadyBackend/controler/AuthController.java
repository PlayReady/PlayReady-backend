package com.playready.PlayReadyBackend.controler;

import com.playready.PlayReadyBackend.dto.AuthDto;
import com.playready.PlayReadyBackend.dto.RegisterDto;
import com.playready.PlayReadyBackend.security.JwtService;

import com.playready.PlayReadyBackend.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final AuthService service;

    public AuthController(AuthenticationManager man, JwtService jwtservice, AuthService authservice) {
        this.authManager = man;
        this.jwtService = jwtservice;
        this.service = authservice;
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> signIn(@RequestBody AuthDto authDto) {
        UsernamePasswordAuthenticationToken up =
                new UsernamePasswordAuthenticationToken(authDto.username, authDto.password);

        try {
            Authentication auth = authManager.authenticate(up);

            UserDetails ud = (UserDetails) auth.getPrincipal();
            String token = jwtService.generateToken(ud);

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .body(token);
        } catch (AuthenticationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String username = service.registerUser(registerDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + username).toUriString());
        return ResponseEntity.created(uri).body("Succes");
    }
}
