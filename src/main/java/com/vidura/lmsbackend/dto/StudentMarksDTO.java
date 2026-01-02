package com.vidura.lmsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentMarksDTO {

    private long id;
    private long studentId;
    private long quizId;
    private long teacherId;
    private List<Long> tagIDs;
    private List<Double> marks;
}
