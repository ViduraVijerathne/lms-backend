package com.vidura.lmsbackend.repository;

import com.vidura.lmsbackend.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<Batch, Long> {
}
