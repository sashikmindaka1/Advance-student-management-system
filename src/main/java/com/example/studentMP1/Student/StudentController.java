package com.example.studentMP1.Student;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    public final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return  studentService.addStudent(student);
    }

    @GetMapping("/get")
    public List<Student> getStudent() {
        return studentService.findAllStudent();
    }
}
