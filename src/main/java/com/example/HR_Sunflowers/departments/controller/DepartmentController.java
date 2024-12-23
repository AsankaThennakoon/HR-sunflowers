package com.example.HR_Sunflowers.departments.controller;

import com.example.HR_Sunflowers.departments.dtos.request.CreateDepartmentDto;
import com.example.HR_Sunflowers.departments.dtos.response.DepartmentGeneralDto;
import com.example.HR_Sunflowers.departments.entity.Department;
import com.example.HR_Sunflowers.departments.services.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/department")
@AllArgsConstructor
public class DepartmentController {


    private DepartmentService departmentService;


    @GetMapping("/getDepartment/{id}")
    public ResponseEntity<DepartmentGeneralDto> getEmployee(@PathVariable Integer id) {


        return departmentService.getDepartment(id);

    }


    @GetMapping("/department")
    public ResponseEntity<List<DepartmentGeneralDto>> getEmployees() {

        return departmentService.getDepartments();

    }

    @PostMapping("/addDepartment")
    public ResponseEntity<Department> addDepartment(
            @RequestBody CreateDepartmentDto createDepartmentDto){
            return departmentService.addDepartment(createDepartmentDto);

    }

    @DeleteMapping("/deleteDepartment")
    public ResponseEntity<String> deleteEmployee(@RequestParam Integer id) {
        return departmentService.deleteDepartment(id);
    }

    @PutMapping("/updateDepartment")
    public ResponseEntity<String> updateEmployee(
            @RequestParam Integer id,
            @RequestBody CreateDepartmentDto createDepartmentDto) {

                return departmentService.updateDepartment(id,createDepartmentDto);
                // Save the file name or path to the employee object

    }
    @GetMapping("/department_count")
    public Long getEmployeeCount(){
        return departmentService.getDepartmentCount();
    }
}
