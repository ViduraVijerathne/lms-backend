package com.vidura.lmsbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {

    @Positive(message = "Quiz ID must be a positive number")
    private long id;

    @Positive(message = "Lesson ID must be a positive number")
    private long lessonID;

    @NotBlank(message = "Quiz title cannot be empty")
    @Size(min = 3, max = 200, message = "Quiz title must be between 3 and 200 characters")
    private String title;

    @NotBlank(message = "Quiz description cannot be empty")
    @Size(max = 1000, message = "Quiz description cannot exceed 1000 characters")
    private String description;

    @NotEmpty(message = "Quiz must contain at least one question")
    private List<
            @NotNull(message = "Question ID cannot be null")
            @Positive(message = "Question ID must be a positive number")
                    Long
            > questions;
}
