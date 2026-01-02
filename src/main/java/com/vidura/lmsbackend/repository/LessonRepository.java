package com.vidura.lmsbackend.repository;

import com.vidura.lmsbackend.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

interface LessonRepository extends JpaRepository<Lesson, Long> {
}
