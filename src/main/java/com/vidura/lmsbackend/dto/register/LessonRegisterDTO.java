package com.vidura.lmsbackend.dto.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LessonRegisterDTO {
    private String name;
    private String description;
    private String thumbnail;
    private Long lessonPackID;
    private List<String> tags = new ArrayList<>();
}
