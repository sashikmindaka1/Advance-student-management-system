package com.example.studentMP1.Department;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String deptName;

    @Column(nullable = false)
    private String shortCode;



}
