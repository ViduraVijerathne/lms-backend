package com.vidura.lmsbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonAttachmentDTO {

    private long id;
    private long lessonId;
    private List<String> links;
}
