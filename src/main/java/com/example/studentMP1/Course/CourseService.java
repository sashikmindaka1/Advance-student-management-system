package com.example.studentMP1.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // 1. අලුත් Course එකක් Add කරනවා (උදා: Java, Database)
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    // 2. Department ID එක දුන්නම ඒකට අදාළ Courses ටික දෙනවා
    // (අපි StudentService එකෙත් මේක පාවිච්චි කළා, දැන් Frontend එකටත් ඕන වෙනවා)
    public List<Course> getCoursesByDepartmentId(Long deptId) {
        return courseRepository.findByDepartmentId(deptId);
    }

    // 3. ඔක්කොම Courses ඕන නම්
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}