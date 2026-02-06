package com.example.studentMP1.Lecturer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LecturerService {

    @Autowired
    private LecturerRepository lecturerRepository;

    // 1. Lecturer Register වෙනවා
    public Lecturer registerLecturer(Lecturer lecturer) {
        // මෙතන ඕන නම් Email එක කලින් තියෙනවද කියලා බලන logic එක දාන්න පුළුවන්
        return lecturerRepository.save(lecturer);
    }

    // 2. Lecturer Login වෙනවා (Email & Password Check)
    public Lecturer loginLecturer(String email, String password) {
        // Email එකෙන් හොයනවා
        Optional<Lecturer> lecturerOptional = lecturerRepository.findByEmail(email);

        if (lecturerOptional.isPresent()) {
            Lecturer lecturer = lecturerOptional.get();
            // Password සමානද බලනවා
            if (lecturer.getPassword().equals(password)) {
                return lecturer; // Success
            }
        }
        return null; // Failed
    }
}