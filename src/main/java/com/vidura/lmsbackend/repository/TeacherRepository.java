package com.vidura.lmsbackend.repository;

import com.vidura.lmsbackend.entity.Teacher;
import com.vidura.lmsbackend.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findTeacherByUser(User user);
}
