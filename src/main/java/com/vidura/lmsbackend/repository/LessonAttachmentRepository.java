package com.vidura.lmsbackend.repository;

import com.vidura.lmsbackend.entity.LessonAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

interface LessonAttachmentRepository extends JpaRepository<LessonAttachment, Long> {
}
