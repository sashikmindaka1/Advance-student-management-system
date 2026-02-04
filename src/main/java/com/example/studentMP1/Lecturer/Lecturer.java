package com.example.studentMP1.Lecturer;

import com.example.studentMP1.Course.Course;
import com.example.studentMP1.Department.Department;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Lecturer")
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lecturerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false , unique = true)
    private String email;

    private String password;

    private String role;

    private String mobileNumber;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;


}
