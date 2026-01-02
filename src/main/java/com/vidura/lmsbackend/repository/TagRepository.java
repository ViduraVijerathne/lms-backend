package com.vidura.lmsbackend.repository;

import com.vidura.lmsbackend.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
