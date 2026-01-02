package com.vidura.lmsbackend.entity;

import com.vidura.lmsbackend.dto.SubjectDTO;
import com.vidura.lmsbackend.repository.SubjectRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "subjects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private boolean isActive;

    public SubjectDTO toDTO() {
        SubjectDTO dto = new SubjectDTO();
        dto.setId(id);
        dto.setName(name);
        return dto;
    }
}
