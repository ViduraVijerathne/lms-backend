package com.vidura.lmsbackend.repository;

import com.vidura.lmsbackend.entity.StudentMarks;
import org.springframework.data.jpa.repository.JpaRepository;

interface StudentMarksRepository extends JpaRepository<StudentMarks, Long> {
}
