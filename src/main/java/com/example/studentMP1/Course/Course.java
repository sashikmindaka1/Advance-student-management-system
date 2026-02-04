package com.example.studentMP1.Course;

import com.example.studentMP1.Department.Department;
import com.example.studentMP1.Lecturer.Lecturer;
import com.example.studentMP1.Student.Student;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    private String courseCode;

    @Column(nullable = false)
    private String courseDescription;

    @Column(nullable = false)
    private String semester;


    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;



}
