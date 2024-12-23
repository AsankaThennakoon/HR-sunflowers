package com.example.HR_Sunflowers.departments.services;

import com.example.HR_Sunflowers.departments.dtos.request.CreateDepartmentDto;
import com.example.HR_Sunflowers.departments.dtos.response.DepartmentGeneralDto;
import com.example.HR_Sunflowers.departments.entity.Department;
import com.example.HR_Sunflowers.departments.repsitories.DepartmentRepository;
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
public class DepartmentService {
    private final DepartmentRepository departmentRepository;


    public ResponseEntity<DepartmentGeneralDto> getDepartment(Integer id) {
        DepartmentGeneralDto departmentGeneralDto = new DepartmentGeneralDto();

        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();

            departmentGeneralDto.setName(department.getName());

            departmentGeneralDto.setDepartment_id(department.getDepartment_id());
            departmentGeneralDto.setLocation(department.getLocation());
            departmentGeneralDto.setNumberOfEmployee(department.getNumberOfEmployee());



            return ResponseEntity.ok(departmentGeneralDto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    public ResponseEntity<Department> addDepartment(CreateDepartmentDto createDepartmentDto) {
        Department department = new Department();

        department.setName(createDepartmentDto.getName());
        department.setLocation(createDepartmentDto.getLocation());
        department.setNumberOfEmployee(createDepartmentDto.getNumberOfEmployee());


        departmentRepository.save(department);
        return ResponseEntity.status(201).body(department);
    }

    public ResponseEntity<String> deleteDepartment(Integer id) {
        Optional<Department> optionalDepartment= departmentRepository.findById(id);
        if (optionalDepartment.isPresent()) {
            departmentRepository.deleteById(id);
            return ResponseEntity.ok("Department Deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> updateDepartment(Integer id, CreateDepartmentDto createDepartmentDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()) {
            log.info("Department is founded and id is " + id);
            Department department = optionalDepartment.get();
            department.setName(createDepartmentDto.getName());
            department.setLocation(createDepartmentDto.getLocation());
            department.setNumberOfEmployee(createDepartmentDto.getNumberOfEmployee());


            departmentRepository.save(department);
            return ResponseEntity.ok("Department Successfully updated changed");
        } else {
            return ResponseEntity.notFound().build();

        }
    }

    public ResponseEntity<List<DepartmentGeneralDto>> getDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentGeneralDto> departmentGeneralDtoList = departments.stream()
                .map(department -> {
                    DepartmentGeneralDto dto = new DepartmentGeneralDto();
dto.setDepartment_id(department.getDepartment_id());
                    dto.setName(department.getName());

                    dto.setLocation(department.getLocation());

                    dto.setNumberOfEmployee(department.getNumberOfEmployee());


                    return dto;
                })
                .toList();
        return ResponseEntity.ok(departmentGeneralDtoList);
    }

    public Long getDepartmentCount() {
        return departmentRepository.count();
    }


}
