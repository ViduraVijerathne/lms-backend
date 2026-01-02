package com.vidura.lmsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
    private Long id;

    private String name;
    private String profileURL;
    private String email;

    private long userID;
    private long subjectID;
    private List<Long> tagIDs;
}
