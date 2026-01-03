package com.vidura.lmsbackend.repository;

import com.vidura.lmsbackend.entity.Batch;
import com.vidura.lmsbackend.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BatchRepository extends JpaRepository<Batch, Long> {
    List<Batch> findBatchesByTeacher(Teacher teacher);

    Optional<Batch> findBatchesById(Long id);

    List<Batch> getBatchesByTeacher(Teacher teacher);
}
