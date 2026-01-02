package com.vidura.lmsbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonPackDTO {

    private Long id;

    private String name;
    private String description;
    private String imageURL;
    private LocalDateTime createdAt;
    private long batchID;
    private boolean isActive;
}
