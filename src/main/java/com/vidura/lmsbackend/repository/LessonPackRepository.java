package com.vidura.lmsbackend.repository;

import com.vidura.lmsbackend.entity.LessonPack;
import org.springframework.data.jpa.repository.JpaRepository;

interface LessonPackRepository extends JpaRepository<LessonPack, Long> {
}
