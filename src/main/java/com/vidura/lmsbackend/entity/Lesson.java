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
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_pack_id")
    private LessonPack lessonPack;

    // Assuming a lesson can have multiple tags
    @ManyToMany
    @JoinTable(
            name = "lesson_tags",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();
}