package com.portal.mcq_portal.controller;
import com.portal.mcq_portal.model.User;
import com.portal.mcq_portal.repository.UserRepository;
import com.portal.mcq_portal.model.Submission;
import com.portal.mcq_portal.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/teacher")
@CrossOrigin(origins = "http://localhost:5500")
public class TeacherController {

    private final UserRepository userRepository;
    public TeacherController(SubmissionRepository submissionRepository, UserRepository userRepository) {
    this.submissionRepository = submissionRepository;
    this.userRepository = userRepository;
}


    @Autowired
    private SubmissionRepository submissionRepository;

    @GetMapping("/results")
    public ResponseEntity<?> getAllResults() {

        List<Submission> submissions = submissionRepository.findAll();

        // Sort highest score first
        submissions.sort((a, b) -> b.getScore() - a.getScore());

        // Top 3 students
        List<String> top3 = new ArrayList<>();
        for (int i = 0; i < Math.min(3, submissions.size()); i++) {
            top3.add(submissions.get(i).getStudentName() + " (" + submissions.get(i).getScore() + ")");
        }

        // Pass fail (pass = >=3)
        long pass = submissions.stream().filter(s -> s.getScore() >= 3).count();
        long fail = submissions.size() - pass;

        // Table data
        List<Map<String, Object>> resultTable = new ArrayList<>();
        for (Submission s : submissions) {
            Map<String, Object> row = new HashMap<>();
            row.put("student", s.getStudentName());
            row.put("email", s.getStudentEmail());
            row.put("subject", s.getSubject());
            row.put("score", s.getScore());
            row.put("date", s.getSubmittedAt());
            row.put("status", s.getScore() >= 3 ? "Pass" : "Fail");
            resultTable.add(row);
        }

        return ResponseEntity.ok(Map.of(
                "top3", top3,
                "pass", pass,
                "fail", fail,
                "results", resultTable
        ));
    }
    @GetMapping("/students/count")
public long getStudentCount() {
    return userRepository.countByRole("STUDENT");

}


}
