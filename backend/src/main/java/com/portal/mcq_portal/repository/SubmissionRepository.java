package com.portal.mcq_portal.repository;

import com.portal.mcq_portal.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    
    // ✅ Get all submissions
    List<Submission> findAll();

    // ✅ (Optional) Get submissions by student email
    List<Submission> findByStudentEmail(String email);
}
