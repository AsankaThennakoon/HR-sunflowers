package com.example.HR_Sunflowers.employee.dtos.request;

import lombok.Data;

@Data
public class CreateEmployeeDto {


    private String name;
    private String email;
    private String address;
    private String position;
}
