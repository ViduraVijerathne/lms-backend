package com.vidura.lmsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchDTO {

    private long id;
    private String name;
    private long teacherID;
    private String teacherEmail;
    private boolean isActive;
}
