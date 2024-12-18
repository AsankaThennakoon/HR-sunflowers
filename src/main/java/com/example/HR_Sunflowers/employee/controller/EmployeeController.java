package com.example.HR_Sunflowers.employee.controller;

import com.example.HR_Sunflowers.employee.dtos.request.CreateEmployeeDto;
import com.example.HR_Sunflowers.employee.dtos.response.EmployeeGeneralDto;
import com.example.HR_Sunflowers.employee.entity.Employee;
import com.example.HR_Sunflowers.employee.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<EmployeeGeneralDto> getEmployee(@PathVariable Integer id){


        return employeeService.getEmployee(id);

    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addStudent(@RequestBody CreateEmployeeDto createEmployeeDto){
        return employeeService.addEmployee(createEmployeeDto);
    }

    @DeleteMapping("/deleteEmployee")
    public ResponseEntity<String> deleteEmployee(@RequestParam Integer id){
        return  employeeService.deleteEmployee(id);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<String> updateEmployee(@RequestParam Integer id,@RequestParam String newName){
        return employeeService.updateEmployee(id,newName);
    }
}
