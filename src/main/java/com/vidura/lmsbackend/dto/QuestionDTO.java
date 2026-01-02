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
public class QuestionDTO {

    @Positive(message = "Question ID must be a positive number")
    private long id;

    @NotBlank(message = "Question text cannot be empty")
    @Size(min = 5, max = 1000, message = "Question text must be between 5 and 1000 characters")
    private String question;

    @NotEmpty(message = "At least one answer ID is required")
    private List<
            @NotNull(message = "Answer ID cannot be null")
            @Positive(message = "Answer ID must be a positive number")
                    Long
            > answerIDs;

    @NotNull(message = "Correct answer ID must be provided")
    @Positive(message = "Correct answer ID must be a positive number")
    private Long correctAnswerID;
}
