package com.example.demo.loginjwt.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.loginjwt.Jwt.JWTService;
import com.example.demo.loginjwt.Repository.UserRepository;
import com.example.demo.loginjwt.User.Role;
import com.example.demo.loginjwt.User.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserDetails user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
        String authorities = user.getAuthorities().toString();
        String username = user.getUsername();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                    .token(token)
                    .username(username)
                    .authorities(authorities)
                    .build();
    }

    public AuthResponse registerUser(RegisterRequest registerRequest){
        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role(Role.USER)
                .build();
            userRepository.save(user);
        return AuthResponse.builder()
                    .token(jwtService.getToken(user))
                    .build();
    }

    public AuthResponse registerAdmin(RegisterRequest registerRequest){
        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role(Role.ADMIN)
                .build();
            userRepository.save(user);
        return AuthResponse.builder()
                    .token(jwtService.getToken(user))
                    .build();
    }
}
