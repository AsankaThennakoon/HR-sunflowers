package com.example.HR_Sunflowers.departments.dtos.response;

import lombok.Data;

@Data //for getters and setters
public class DepartmentGeneralDto {

    private Integer department_id;
    private String name;
    private String location;
    private Integer numberOfEmployee;

}
