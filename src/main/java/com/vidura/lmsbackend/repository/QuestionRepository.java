package com.vidura.lmsbackend.repository;

import com.vidura.lmsbackend.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

interface QuestionRepository extends JpaRepository<Question, Long> {
}
