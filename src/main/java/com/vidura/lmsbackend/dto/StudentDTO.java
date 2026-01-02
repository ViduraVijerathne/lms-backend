package com.vidura.lmsbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private long id;
    private String name;
    private long userId;
    private List<Long> enrolledLessonPackIDs;
}
