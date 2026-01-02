package com.vidura.lmsbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
    private long id;
    private long lessonID;
    private String title;
    private String description;
    private List<Long> questions;
}
