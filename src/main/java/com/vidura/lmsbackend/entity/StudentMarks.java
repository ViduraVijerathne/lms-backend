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
@Table(name = "student_marks")
public class StudentMarks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToMany
    @JoinTable(
            name = "marks_tags",
            joinColumns = @JoinColumn(name = "mark_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();

    // Use ElementCollection for list of Double
    @ElementCollection
    @CollectionTable(name = "student_score_history", joinColumns = @JoinColumn(name = "mark_id"))
    @Column(name = "score")
    private List<Double> marks = new ArrayList<>();
}