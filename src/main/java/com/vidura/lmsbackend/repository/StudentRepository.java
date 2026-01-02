package com.vidura.lmsbackend.repository;

import com.vidura.lmsbackend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

interface StudentRepository extends JpaRepository<Student, Long> {
}
