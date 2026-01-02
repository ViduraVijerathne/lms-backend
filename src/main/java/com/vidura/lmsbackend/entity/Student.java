package com.vidura.lmsbackend.entity;

import com.vidura.lmsbackend.security.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Use ManyToMany for enrollments (A student has many packs, a pack has many students)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_enrollments",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_pack_id")
    )
    private List<LessonPack> enrolledLessonPacks = new ArrayList<>();
}