package com.portal.mcq_portal.repository;
import com.portal.mcq_portal.model.User;
import com.portal.mcq_portal.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByTeacher(User teacher);
    long countByTeacher(User teacher);

    // Add this method
    List<Test> findByIsPublishedTrue();
}