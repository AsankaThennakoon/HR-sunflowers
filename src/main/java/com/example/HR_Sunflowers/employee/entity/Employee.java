package com.example.HR_Sunflowers.employee.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor          //this one can use as all arg constructor
@NoArgsConstructor
@Data //this one is use instead of getter/setter/toString method using lombook
public class Employee {

    @Id// Automatically generate unique IDs
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)  // Name is required
    private String name;

    @Column(nullable = false, unique = true)  // Email is required and must be unique
    private String email;
    @Column(nullable = false)
    private String password;

    private String address;
    private String position;
    private Double salary;
    private String image;     // New field
    @Column(name = "category_id")
    private Integer categoryId;


}

