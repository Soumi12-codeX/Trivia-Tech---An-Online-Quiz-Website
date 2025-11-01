package com.portal.mcq_portal.repository;

import com.portal.mcq_portal.model.Option;
import com.portal.mcq_portal.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findByQuestion(Question question);
}
