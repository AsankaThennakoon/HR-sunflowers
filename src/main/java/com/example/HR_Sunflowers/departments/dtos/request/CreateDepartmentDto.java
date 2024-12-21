package com.example.HR_Sunflowers.departments.dtos.request;

import lombok.Data;

@Data
public class CreateDepartmentDto {


    private String name;
    private String location;
    private Integer numberOfEmployee;

}
