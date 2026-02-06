package com.example.studentMP1.Attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // 1. Lecturer: අලුත් QR Code එකක් ඉල්ලනවා (Frontend එක Timer දාලා ඉල්ලන්න ඕන)
    @GetMapping("/generate/{courseId}")
    public String generateQr(@PathVariable Long courseId) {
        return attendanceService.generateLectureQr(courseId);
    }

    // 2. Student: ස්කෑන් කරපු QR එක එවනවා
    // (URL එක: /api/attendance/mark?studentId=1&qr=5:abc-123)
    @PostMapping("/mark")
    public ResponseEntity<String> markAttendance(@RequestParam Long studentId, @RequestParam String qr) {
        boolean success = attendanceService.markAttendance(studentId, qr);

        if (success) {
            return ResponseEntity.ok("Attendance Marked Successfully!");
        } else {
            return ResponseEntity.badRequest().body("Invalid or Expired QR Code.");
        }
    }
}