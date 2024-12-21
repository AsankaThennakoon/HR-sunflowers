package com.example.HR_Sunflowers.departments.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor          //this one can use as all arg constructor
@NoArgsConstructor
@Data //this one is use instead of getter/setter/toString method using lombook
public class Department {

    @Id// Automatically generate unique IDs
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer department_id;

    @Column(nullable = false)  // Name is required
    private String name;
    private String location;
    private Integer numberOfEmployee;




}

