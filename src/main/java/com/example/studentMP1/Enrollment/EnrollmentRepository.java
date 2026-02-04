package com.example.studentMP1.Enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {

    List<Enrollment> findByStudentId(long studentId);

    List<Enrollment> findByCourseId(long courseId);
}
