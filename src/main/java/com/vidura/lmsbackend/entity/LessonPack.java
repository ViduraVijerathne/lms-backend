package com.vidura.lmsbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lesson_packs")
public class LessonPack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String imageURL;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now(); // Default to now

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id")
    private Batch batch;

    private boolean isActive;
}