package com.example.studentMP1.Attendance;

import com.example.studentMP1.Course.Course;
import com.example.studentMP1.Student.Student;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime; // ආපු දවස සහ වෙලාව
    private String status;          // "PRESENT"

    // කවුද ආවේ? (Student)
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // මොන පන්තියටද ආවේ? (Course)
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}