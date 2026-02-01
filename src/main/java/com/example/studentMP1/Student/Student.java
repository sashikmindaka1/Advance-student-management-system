package com.example.studentMP1.Student;


import com.example.studentMP1.Course.Course;
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
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate dob;

    @ManyToMany
    @JoinTable(
            name = "student_course_enrollment",
            joinColumns = @JoinColumn (name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Course> courses;

}
