package com.example.studentMP1.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Student")
public class Student {
    @Id
    private Long id;
    private String name;
    private String email;
    private int dob;


}
