package com.vidura.lmsbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lesson_attachments")
public class LessonAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    // Use ElementCollection for simple lists of basic types (Strings)
    @ElementCollection
    @CollectionTable(name = "attachment_links", joinColumns = @JoinColumn(name = "attachment_id"))
    @Column(name = "link")
    private List<String> links;
}