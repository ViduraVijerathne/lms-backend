package com.vidura.lmsbackend.repository;

import com.vidura.lmsbackend.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

interface QuizRepository extends JpaRepository<Quiz, Long> {
}
