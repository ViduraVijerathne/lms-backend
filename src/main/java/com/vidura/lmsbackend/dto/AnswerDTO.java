package com.vidura.lmsbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {

    @Positive(message = "Answer ID must be a positive number")
    private long id;

    @NotBlank(message = "Answer cannot be empty")
    @Size(
            min = 1,
            max = 500,
            message = "Answer must be between 1 and 500 characters"
    )
    private String answer;

    @Positive(message = "Question ID must be a positive number")
    private long questionID;
}
