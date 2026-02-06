package com.example.studentMP1.Enrollment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // --- The Main Logic: Marks දාලා Grade එක හදන තැන ---
    public Enrollment assignMarks(Long enrollmentId, Double marks) {

        // 1. මුලින්ම අදාළ Enrollment එක (හිස් රිපෝට් කාඩ් එක) හොයාගන්නවා
        Optional<Enrollment> enrollmentOptional = enrollmentRepository.findById(enrollmentId);

        if (enrollmentOptional.isPresent()) {
            Enrollment enrollment = enrollmentOptional.get();

            // 2. ලකුණු ටික දානවා
            enrollment.setMarks(marks);

            // 3. ලකුණු අනුව Grade එක තීරණය කරනවා (Business Logic) 🧠
            String grade;
            String status;

            if (marks >= 75) {
                grade = "A";
                status = "COMPLETED";
            } else if (marks >= 65) {
                grade = "B";
                status = "COMPLETED";
            } else if (marks >= 55) {
                grade = "C";
                status = "COMPLETED";
            } else if (marks >= 35) {
                grade = "S";  // සාමාන්‍ය Pass එකක්
                status = "COMPLETED";
            } else {
                grade = "F";  // Fail
                status = "REPEAT"; // ආයේ කරන්න ඕනේ
            }

            // 4. තීරණය කරපු Grade එකයි Status එකයි සෙට් කරනවා
            enrollment.setGrade(grade);
            enrollment.setStatus(status);

            // 5. ඔක්කොම හරි, දැන් Save කරනවා
            return enrollmentRepository.save(enrollment);
        }

        return null; // Enrollment එක හොයාගන්න බැරි වුනොත්
    }
}