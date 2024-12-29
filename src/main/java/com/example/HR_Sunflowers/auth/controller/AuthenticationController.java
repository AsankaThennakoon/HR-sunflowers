package com.example.HR_Sunflowers.auth.controller;

import com.example.HR_Sunflowers.auth.dtos.LoginUserDto;
import com.example.HR_Sunflowers.auth.dtos.RegisterUserDto;
import com.example.HR_Sunflowers.auth.entity.User;
import com.example.HR_Sunflowers.auth.responses.LoginResponse;
import com.example.HR_Sunflowers.auth.services.AuthenticationService;
import com.example.HR_Sunflowers.auth.services.JwtService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@AllArgsConstructor
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;


    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        System.out.println("Incoming request: " + loginUserDto);
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token){
        String jwtToken=token.replace("Bearer ","");
        authenticationService.logoutUser(jwtToken);
        return  ResponseEntity.ok("Logged out successfully");
    }
}