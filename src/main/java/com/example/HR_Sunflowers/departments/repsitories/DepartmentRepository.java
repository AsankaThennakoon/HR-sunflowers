package com.example.HR_Sunflowers.departments.repsitories;

import com.example.HR_Sunflowers.departments.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {


    Optional<Department> findByName(String name);
}
