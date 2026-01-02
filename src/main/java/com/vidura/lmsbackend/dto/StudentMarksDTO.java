package com.vidura.lmsbackend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentMarksDTO {

    @Positive(message = "ID must be a positive number")
    private long id;

    @Positive(message = "Student ID must be a positive number")
    private long studentId;

    @Positive(message = "Quiz ID must be a positive number")
    private long quizId;

    @Positive(message = "Teacher ID must be a positive number")
    private long teacherId;

    @NotEmpty(message = "At least one tag ID is required")
    private List<
            @NotNull(message = "Tag ID cannot be null")
            @Positive(message = "Tag ID must be a positive number")
                    Long
            > tagIDs;

    @NotEmpty(message = "Marks list cannot be empty")
    private List<
            @NotNull(message = "Marks cannot be null")
            @DecimalMin(value = "0.0", inclusive = true, message = "Marks cannot be negative")
            @DecimalMax(value = "100.0", inclusive = true, message = "Marks cannot exceed 100")
                    Double
            > marks;
}
