package com.example.HR_Sunflowers.auth.services;

import com.example.HR_Sunflowers.auth.dtos.LoginUserDto;
import com.example.HR_Sunflowers.auth.dtos.RegisterUserDto;
import com.example.HR_Sunflowers.auth.entity.User;
import com.example.HR_Sunflowers.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public User signup(RegisterUserDto input) {
        var user = User.builder()
                .userName(input.getUserName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .build();

        var jwtToken =jwtService.generateToken(user);

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()

                )
        );
        var user=userRepository.findByEmail(input.getEmail()).orElseThrow();
        var jwtToken=jwtService.generateToken(user);

        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}