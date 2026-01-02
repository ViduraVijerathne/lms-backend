package com.vidura.lmsbackend.repository;

import com.vidura.lmsbackend.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

interface AnswerRepository extends JpaRepository<Answer, Long> {
}
