package com.example.HR_Sunflowers.employee.services;

import com.example.HR_Sunflowers.employee.dtos.request.CreateEmployeeDto;
import com.example.HR_Sunflowers.employee.dtos.response.EmployeeGeneralDto;
import com.example.HR_Sunflowers.employee.entity.Employee;
import com.example.HR_Sunflowers.employee.repsitories.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j //this one is used to log error and print thing in the console
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public ResponseEntity<EmployeeGeneralDto> getEmployee(Integer id) {
        EmployeeGeneralDto employeeGeneralDto = new EmployeeGeneralDto();

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();

            employeeGeneralDto.setName(employee.getName());

            employeeGeneralDto.setEmail(employee.getEmail());

            employeeGeneralDto.setAddress(employee.getAddress());

            employeeGeneralDto.setSalary(employee.getSalary());

            employeeGeneralDto.setImage(employee.getImage());
            employeeGeneralDto.setPosition(employee.getPosition());

            return ResponseEntity.ok(employeeGeneralDto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    public ResponseEntity<Employee> addEmployee(CreateEmployeeDto createEmployeeDto, String imageName) {
        Employee employee = new Employee();

        employee.setName(createEmployeeDto.getName());
        employee.setEmail(createEmployeeDto.getEmail());
        employee.setAddress(createEmployeeDto.getAddress());
        employee.setPosition(createEmployeeDto.getPosition());
        employee.setPassword(passwordEncoder.encode(createEmployeeDto.getPassword())); // Hash password
        employee.setSalary(createEmployeeDto.getSalary());
        employee.setImage(imageName); // Set uploaded image name
        employee.setCategoryId(createEmployeeDto.getCategoryId());

        employeeRepository.save(employee);
        return ResponseEntity.status(201).body(employee);
    }

    public ResponseEntity<String> deleteEmployee(Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            employeeRepository.deleteById(id);
            return ResponseEntity.ok("Employee Deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> updateEmployee(Integer id, CreateEmployeeDto createEmployeeDto, String imageName) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            log.info("Employee is founded and id is " + id);
            Employee employee = optionalEmployee.get();
            employee.setName(createEmployeeDto.getName());
            employee.setEmail(createEmployeeDto.getEmail());
            employee.setAddress(createEmployeeDto.getAddress());
            employee.setPosition(createEmployeeDto.getPosition());
            employee.setPassword(passwordEncoder.encode(createEmployeeDto.getPassword())); // Hash password
            employee.setSalary(createEmployeeDto.getSalary());
            employee.setImage(imageName); // Set uploaded image name
            employee.setCategoryId(createEmployeeDto.getCategoryId());


            employeeRepository.save(employee);
            return ResponseEntity.ok("Employee Successfully updated changed");
        } else {
            return ResponseEntity.notFound().build();

        }
    }

    public ResponseEntity<List<EmployeeGeneralDto>> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeGeneralDto> employeeDtos = employees.stream()
                .map(employee -> {
                    EmployeeGeneralDto dto = new EmployeeGeneralDto();

                    dto.setId(employee.getId());
                    dto.setName(employee.getName());

                    dto.setEmail(employee.getEmail());

                    dto.setAddress(employee.getAddress());

                    dto.setSalary(employee.getSalary());
                    dto.setPosition(employee.getPosition());

                    dto.setImage(employee.getImage());
                    return dto;
                })
                .toList();
        return ResponseEntity.ok(employeeDtos);
    }

}
