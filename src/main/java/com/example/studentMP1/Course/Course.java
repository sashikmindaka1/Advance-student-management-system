package com.example.studentMP1.Course;

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
    private long courseId;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    private String courseCode;

    @Column(nullable = false)
    private String courseDescription;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;



}
