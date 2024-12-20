package com.example.HR_Sunflowers.employee.repsitories;

import com.example.HR_Sunflowers.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {


    Optional<Employee> findByName(String name);
}
