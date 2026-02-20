package com.zest.productapi.controller;

import com.zest.productapi.dto.LoginRequest;
import com.zest.productapi.dto.LoginResponse;
import com.zest.productapi.security.JwtUtil;
import com.zest.productapi.service.impl.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          CustomUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    // Login endpoint → returns access + refresh token
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        var roles = authentication.getAuthorities()
                .stream().map(a -> a.getAuthority()).collect(Collectors.toList());

        String accessToken = jwtUtil.generateToken(request.getUsername(), roles);
        String refreshToken = jwtUtil.generateRefreshToken(request.getUsername());

        return ResponseEntity.ok(new LoginResponse(accessToken, refreshToken));
    }

    // Refresh token endpoint → returns new access + refresh token
    @PostMapping("/refresh-token")
    public ResponseEntity<LoginResponse> refreshToken(@RequestBody Map<String, String> request) {
        String oldRefreshToken = request.get("refreshToken");

        if (!jwtUtil.validateRefreshToken(oldRefreshToken)) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }

        String username = jwtUtil.getUsernameFromToken(oldRefreshToken);
        var userDetails = userDetailsService.loadUserByUsername(username);
        var roles = userDetails.getAuthorities()
                .stream().map(a -> a.getAuthority()).collect(Collectors.toList());

        String newAccessToken = jwtUtil.generateToken(username, roles);
        String newRefreshToken = jwtUtil.generateRefreshToken(username);

        return ResponseEntity.ok(new LoginResponse(newAccessToken, newRefreshToken));
    }
}