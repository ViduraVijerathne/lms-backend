package com.vidura.lmsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonDTO {
    private long id;
    private String name;
    private String description;
    private String thumbnail;

    private long lessonPackID;
    private List<Long> tagIDs;
}
