package com.example.HR_Sunflowers.auth.services;


import com.example.HR_Sunflowers.auth.dtos.AdminGeneralDto;
import com.example.HR_Sunflowers.auth.entity.User;
import com.example.HR_Sunflowers.auth.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public List<AdminGeneralDto> allUsers() {
        List<User> admins = new ArrayList<>();

        userRepository.findAll().forEach(admins::add);

        List<AdminGeneralDto> adminList = admins.stream().map(admin -> {
            AdminGeneralDto adminDto = new AdminGeneralDto();
            adminDto.setUserName(admin.getUsername());
            adminDto.setEmail(admin.getEmail());

            return adminDto;

        }).toList();


        return adminList;
    }

    public Long getAdminCount() {
        return userRepository.count();
    }
}