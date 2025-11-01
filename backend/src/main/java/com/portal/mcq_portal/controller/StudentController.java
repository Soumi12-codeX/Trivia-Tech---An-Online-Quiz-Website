package com.portal.mcq_portal.controller;

import com.portal.mcq_portal.model.*;
import com.portal.mcq_portal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "http://localhost:5500")
public class StudentController {

    @Autowired private TestRepository testRepository;
    @Autowired private SubmissionRepository submissionRepository;
    @Autowired private UserRepository userRepository;

    private User getStudent(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @GetMapping("/available-tests")
    public List<Test> getAvailableTests() {
        return testRepository.findByIsPublishedTrue();
    }

    @PostMapping("/submit/{subject}")
public ResponseEntity<?> submitStaticQuiz(
        @PathVariable String subject,
        @RequestBody Map<String, Object> data
) {
    String studentName = (String) data.get("studentName");
    String studentEmail = (String) data.get("studentEmail");
    Integer score = (Integer) data.get("score");

    Submission submission = new Submission();
    submission.setStudentName(studentName);
    submission.setStudentEmail(studentEmail);
    submission.setSubject(subject);
    submission.setScore(score);

    submissionRepository.save(submission);

    return ResponseEntity.ok(Map.of(
            "message", "Score submitted",
            "subject", subject,
            "score", score
    ));
}



}
