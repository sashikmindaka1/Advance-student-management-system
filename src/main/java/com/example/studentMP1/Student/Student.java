package com.example.studentMP1.Student;

import com.example.studentMP1.Department.Department; // Department එක import කරන්න
import com.example.studentMP1.Course.Course;
import com.example.studentMP1.Enrollment.Enrollment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(nullable = false)
    private String name; // Note: Java වල variable names පටන් ගන්නේ simple අකුරෙන්

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;           // "STUDENT"

    @Column(nullable = false)
    private int currentSemester;

    private String feeStatus;      // "PAID" or "PENDING"
    private double gpa;            // Current GPA
    private String qrToken;        // QR Code String


    // 1. Student අනිවාර්යයෙන්ම Degree (Department) එකකට අයිති වෙන්න ඕනේ
    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    // 2. Student ගේ ලකුණු සහ Modules බලාගන්න
    @OneToMany(mappedBy = "student")
    @JsonIgnore // Infinite Loop වලක්වන්න
    private List<Enrollment> enrollments;
}