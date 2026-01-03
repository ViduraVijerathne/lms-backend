package com.vidura.lmsbackend.repository;

import com.vidura.lmsbackend.entity.Tag;
import com.vidura.lmsbackend.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findTagByTagAndTeacher(String tag, Teacher teacher);
}
