package com.vidura.lmsbackend.entity;

import com.vidura.lmsbackend.dto.BatchDTO;
import com.vidura.lmsbackend.dto.SubjectDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "batches")
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    private boolean isActive;

    public BatchDTO toDTO() {
        BatchDTO dto = new BatchDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setTeacherID(teacher.getId());
        dto.setIsActive(isActive);
        return dto;
    }
}