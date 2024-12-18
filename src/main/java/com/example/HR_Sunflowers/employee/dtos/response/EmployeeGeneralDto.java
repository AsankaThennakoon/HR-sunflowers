package com.example.HR_Sunflowers.employee.dtos.response;

import lombok.Data;

@Data //for getters and setters
public class EmployeeGeneralDto {
    private Integer id;
    private String name;
    private String position;
}
