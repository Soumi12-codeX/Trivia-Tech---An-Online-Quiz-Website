package com.portal.mcq_portal.repository;

import com.portal.mcq_portal.model.Question;
import com.portal.mcq_portal.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByTest(Test test);
    
    // Count questions created by a specific teacher (email)
    Long countByCreatedBy(String createdBy);
}
