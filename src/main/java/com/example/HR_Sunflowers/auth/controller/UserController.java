package com.example.HR_Sunflowers.auth.controller;
import com.example.HR_Sunflowers.auth.dtos.AdminGeneralDto;
import com.example.HR_Sunflowers.auth.entity.User;
import com.example.HR_Sunflowers.auth.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/admin")
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/count")
    public Long adminCount() {


        return userService.getAdminCount();
    }

    @GetMapping("/admin_list")
    public ResponseEntity<List<AdminGeneralDto>> getALlAdmin() {
        List <AdminGeneralDto> admins = userService.allUsers();

        return ResponseEntity.ok(admins);
    }
}