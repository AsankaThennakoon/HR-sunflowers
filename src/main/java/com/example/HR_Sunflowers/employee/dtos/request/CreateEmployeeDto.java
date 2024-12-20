package com.example.HR_Sunflowers.employee.dtos.request;

import lombok.Data;

@Data
public class CreateEmployeeDto {


    private String name;
    private String email;
    private String address;
    private String position;
    private String password; // New field for password
    private Double salary;   // New field for salary
    private Integer categoryId;
}
