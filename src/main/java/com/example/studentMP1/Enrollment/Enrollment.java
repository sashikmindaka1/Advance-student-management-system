package com.example.studentMP1.Enrollment;

import com.example.studentMP1.Course.Course;
import com.example.studentMP1.Student.Student;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //  Student ?
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    //  Module (Course) ?
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // --- Grades & Marks ---
    private double marks;          // marks (: 75.0)
    private String grade;          // Grade (: A, B)
    private String status;         // "ENROLLED", "COMPLETED"
    private int semester;          //  Semester ?
}