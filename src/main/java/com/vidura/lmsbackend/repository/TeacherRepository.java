package com.vidura.lmsbackend.repository;

import com.vidura.lmsbackend.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
