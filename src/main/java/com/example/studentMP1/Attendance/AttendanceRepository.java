package com.example.studentMP1.Attendance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    // ළමයෙක්ගේ Attendance බලන්න ඕන වුනොත්
    List<Attendance> findByStudentId(Long studentId);

    // Course එකක Attendance බලන්න ඕන වුනොත්
    List<Attendance> findByCourseId(Long courseId);
}