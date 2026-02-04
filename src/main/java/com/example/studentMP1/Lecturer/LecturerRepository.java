package com.example.studentMP1.Lecturer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

    Optional<Lecturer> findByEmail(String email);
}
