package com.portal.mcq_portal.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description; // ✅ add this field

    private LocalDateTime createdAt; // ✅ add this field

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<Question> questions;

    // ✅ Constructors
    public Test() {
        this.createdAt = LocalDateTime.now();
    }

    private Integer timeLimit;
    private Boolean published = false;


    public Test(String title, Integer timeLimit, Boolean published, User teacher) {
    this.title = title;
    this.timeLimit = timeLimit;
    this.published = published;
    this.teacher = teacher;
}


    // ✅ Getters and Setters
    private boolean isPublished = false;

public boolean isPublished() {
    return isPublished;
}

public void setPublished(boolean published) {
    isPublished = published;
}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;


    }
}
