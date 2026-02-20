//package com.zest.productapi.controller;
//
//import com.zest.productapi.dto.LoginRequest;
//import com.zest.productapi.dto.LoginResponse;
//import com.zest.productapi.security.JwtUtil;
//import com.zest.productapi.service.impl.CustomUserDetailsService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.util.List;
//import java.util.Map;
//
//import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
//
//@ExtendWith(MockitoExtension.class)
//class AuthControllerTest {
//
//    @Mock
//    private AuthenticationManager authenticationManager;
//
//    @Mock
//    private JwtUtil jwtUtil;
//
//    @Mock
//    private CustomUserDetailsService userDetailsService;
//
//    @InjectMocks
//    private AuthController authController;
//
//    private LoginRequest loginRequest;
//
//    @BeforeEach
//    void setUp() {
//        loginRequest = new LoginRequest();
//        loginRequest.setUsername("testuser");
//        loginRequest.setPassword("password");
//    }
//
//    // ✅ Test login success
//    @Test
//    void testLogin_Success() {
//
//        Authentication authentication = mock(Authentication.class);
//
//        when(authenticationManager.authenticate(any()))
//                .thenReturn(authentication);
//
//        when(authentication.getAuthorities())
//                .thenReturn(List.of(new SimpleGrantedAuthority("ROLE_USER")));
//
//        when(jwtUtil.generateToken(eq("testuser"), anyList()))
//                .thenReturn("access-token");
//
//        when(jwtUtil.generateRefreshToken("testuser"))
//                .thenReturn("refresh-token");
//
//        ResponseEntity<LoginResponse> response =
//                authController.login(loginRequest);
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertNotNull(response.getBody());
//        assertEquals("access-token", response.getBody().getAccessToken());
//        assertEquals("refresh-token", response.getBody().getRefreshToken());
//    }
//
//    // ✅ Test refresh token success
//    @Test
//    void testRefreshToken_Success() {
//
//        String oldRefreshToken = "old-refresh-token";
//
//        when(jwtUtil.validateRefreshToken(oldRefreshToken))
//                .thenReturn(true);
//
//        when(jwtUtil.getUsernameFromToken(oldRefreshToken))
//                .thenReturn("testuser");
//
//        User user = new User("testuser", "password",
//                List.of(new SimpleGrantedAuthority("ROLE_USER")));
//
//        when(userDetailsService.loadUserByUsername("testuser"))
//                .thenReturn(user);
//
//        when(jwtUtil.generateToken(eq("testuser"), anyList()))
//                .thenReturn("new-access-token");
//
//        when(jwtUtil.generateRefreshToken("testuser"))
//                .thenReturn("new-refresh-token");
//
//        ResponseEntity<LoginResponse> response =
//                authController.refreshToken(
//                        Map.of("refreshToken", oldRefreshToken));
//
//        assertEquals(200, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals("new-access-token", response.getBody().getAccessToken());
//        assertEquals("new-refresh-token", response.getBody().getRefreshToken());
//    }
//
//    // ✅ Test refresh token invalid
//    @Test
//    void testRefreshToken_InvalidToken() {
//
//        when(jwtUtil.validateRefreshToken("invalid-token"))
//                .thenReturn(false);
//
//        ResponseEntity<LoginResponse> response =
//                authController.refreshToken(
//                        Map.of("refreshToken", "invalid-token"));
//
//        assertEquals(401, response.getStatusCodeValue());
//        assertNull(response.getBody());
//    }