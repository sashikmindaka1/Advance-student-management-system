package com.example.studentMP1.Student;

import com.example.studentMP1.Course.Course;
import com.example.studentMP1.Course.CourseRepository;
import com.example.studentMP1.Enrollment.Enrollment;
import com.example.studentMP1.Enrollment.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    // 1. අපිට අවශ්‍ය Repositories ටික මෙතනට ගන්නවා
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;


    // --- Feature 1: Student Add + Auto Enrollment (The Magic) ---
    public Student addStudent(Student student) {
        // A. මුලින්ම Student ව Save කරනවා
        Student savedStudent = studentRepository.save(student);

        // B. එයාගේ Department ID එක ගන්නවා
        Long deptId = savedStudent.getDepartment().getId();

        // C. ඒ Department එකට අදාළ Courses ඔක්කොම හොයාගන්නවා
        List<Course> departmentCourses = courseRepository.findByDepartmentId(deptId);

        // D. Loop එකක් දාලා හැම Course එකටම Enrollment එකක් හදනවා
        for (Course course : departmentCourses) {
            Enrollment enrollment = new Enrollment();

            enrollment.setStudent(savedStudent); // කවුද ළමයා?
            enrollment.setCourse(course);       // මොකක්ද විෂය?

            // Default අගයන්
            enrollment.setMarks(0.0);
            enrollment.setGrade("PENDING");
            enrollment.setStatus("ENROLLED");

            // Course එකේ Semester එක Enrollment එකටත් දානවා (Optional)
            // enrollment.setSemester(course.getSemester());

            // Save කරනවා
            enrollmentRepository.save(enrollment);
        }

        return savedStudent;
    }


    // --- Feature 2: Login Logic ---
    public Student loginStudent(String email, String password) {
        // 1. Email එකෙන් Student කෙනෙක් ඉන්නවද බලනවා
        Optional<Student> studentOptional = studentRepository.findByEmail(email);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            // 2. Password එක හරිද බලනවා (දැනට normal string compare)
            if (student.getPassword().equals(password)) {
                return student; // Login Success
            }
        }
        return null; // Login Failed (හෝ Error එකක් විසි කරන්න පුළුවන්)
    }


    // --- Feature 3: Get All Students ---
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }


    // --- Feature 4: Get Single Student by ID ---
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
}