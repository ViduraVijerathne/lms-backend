package com.vidura.lmsbackend.entity;

import com.vidura.lmsbackend.dto.TeacherDTO;
import com.vidura.lmsbackend.security.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String profileURL;
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tags = new ArrayList<>();

    public TeacherDTO toDTO() {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(id);
        teacherDTO.setName(name);
        teacherDTO.setProfileURL(profileURL);
        teacherDTO.setEmail(email);
        teacherDTO.setSubjectID(subject.getId());
        teacherDTO.setUserID(user.getId());
        teacherDTO.setSubjectName(subject.getName());

        List<Long> ids = new ArrayList<>();
        for(Tag tag : tags) {
            ids.add(tag.getId());
        }
        teacherDTO.setTagIDs(ids);
        return teacherDTO;
    }
}