package com.vidura.lmsbackend.repository;

import com.vidura.lmsbackend.entity.Batch;
import com.vidura.lmsbackend.entity.LessonPack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LessonPackRepository extends JpaRepository<LessonPack, Long> {
    List<LessonPack> findLessonPacksByBatch(Batch batch);

    Optional<LessonPack> findLessonPacksById(Long id);
}
