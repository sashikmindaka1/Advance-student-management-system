package com.example.studentMP1.Attendance;

import com.example.studentMP1.Course.Course;
import com.example.studentMP1.Course.CourseRepository;
import com.example.studentMP1.Student.Student;
import com.example.studentMP1.Student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    // RAM එකේ තියාගන්නවා දැනට Active වෙලා තියෙන QR Codes ටික
    // Key = Course ID, Value = Random Token
    private final Map<Long, String> activeQrTokens = new ConcurrentHashMap<>();

    // --- 1. Lecturer QR එක හදනවා (Frontend එකෙන් හැම තත්පර 10ට වරක් call කරනවා) ---
    public String generateLectureQr(Long courseId) {
        // අලුත් Random කෝඩ් එකක් හදනවා
        String newToken = UUID.randomUUID().toString();

        // Map එකේ Save කරනවා: "මේ Course එකට දැන් අදාළ වෙන්නේ මේ කෝඩ් එකයි"
        activeQrTokens.put(courseId, newToken);

        // Frontend එකට යවනවා (Format: "COURSEID:TOKEN")
        return courseId + ":" + newToken;
    }

    // --- 2. Student QR එක Scan කරනවා ---
    public boolean markAttendance(Long studentId, String scannedQrString) {
        try {
            // QR String එක කඩනවා (CourseID සහ Token වෙන් කරගන්න)
            // scannedQrString එක එන්නේ "5:abc-123-xyz" වගේ
            String[] parts = scannedQrString.split(":");
            Long courseId = Long.parseLong(parts[0]);
            String tokenFromQr = parts[1];

            // Map එකේ තියෙන ඇත්තම Token එකත් එක්ක සසඳනවා
            String currentActiveToken = activeQrTokens.get(courseId);

            if (currentActiveToken != null && currentActiveToken.equals(tokenFromQr)) {
                // Token එක හරිනම් Attendance දානවා
                Student student = studentRepository.findById(studentId).orElse(null);
                Course course = courseRepository.findById(courseId).orElse(null);

                if (student != null && course != null) {
                    Attendance attendance = new Attendance();
                    attendance.setStudent(student);
                    attendance.setCourse(course);
                    attendance.setDateTime(LocalDateTime.now());
                    attendance.setStatus("PRESENT");

                    attendanceRepository.save(attendance);
                    return true; // Success
                }
            }
            return false; // Token Expired or Invalid

        } catch (Exception e) {
            return false; // Error parsing QR
        }
    }
}