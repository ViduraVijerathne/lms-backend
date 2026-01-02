package com.vidura.lmsbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quizzes") // Corrected spelling from 'quizes'
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    private String title;
    private String description;

    // Cascade ALL ensures saving a Quiz saves its questions automatically
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "quiz_id") // Creates a Unidirectional OneToMany using FK in Question table
    private List<Question> questions = new ArrayList<>();
}