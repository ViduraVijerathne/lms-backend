package com.vidura.lmsbackend.repository;

import com.vidura.lmsbackend.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
