package com.example.HR_Sunflowers.employee.services;

import com.example.HR_Sunflowers.employee.dtos.request.CreateEmployeeDto;
import com.example.HR_Sunflowers.employee.dtos.response.EmployeeGeneralDto;
import com.example.HR_Sunflowers.employee.entity.Employee;
import com.example.HR_Sunflowers.employee.repsitories.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j //this one is used to log error and print thing in the console
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public ResponseEntity<EmployeeGeneralDto> getEmployee(Integer id) {
        EmployeeGeneralDto employeeGeneralDto=new EmployeeGeneralDto();

        Optional<Employee> optionalEmployee=employeeRepository.findById(id);

        if(optionalEmployee.isPresent()){
            Employee employee=optionalEmployee.get();
            employeeGeneralDto.setId(employee.getId());
            employeeGeneralDto.setName(employee.getName());
            employeeGeneralDto.setPosition(employee.getPosition());

            return ResponseEntity.ok(employeeGeneralDto);
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    public ResponseEntity<Employee> addEmployee(CreateEmployeeDto createEmployeeDto) {
        Employee employee=new Employee();

        employee.setName(createEmployeeDto.getName());
        employee.setEmail(createEmployeeDto.getEmail());
        employee.setPosition(createEmployeeDto.getPosition());
        employee.setAddress(createEmployeeDto.getAddress());
        employeeRepository.save(employee);
        return ResponseEntity.status(201).build();

    }

    public ResponseEntity<String> deleteEmployee(Integer id) {
        Optional<Employee> optionalEmployee=employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            employeeRepository.deleteById(id);
            return ResponseEntity.ok("Employee Deleted successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> updateEmployee(Integer id, String newName) {
        Optional<Employee> optionalEmployee=employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            log.info("Employee is founded and id is "+id);
            Employee employee=optionalEmployee.get();
            employee.setName(newName);
            employeeRepository.save(employee);
            return ResponseEntity.ok("Employee Name Successfully changed");
        }else{
            return ResponseEntity.notFound().build();

        }
    }
}
