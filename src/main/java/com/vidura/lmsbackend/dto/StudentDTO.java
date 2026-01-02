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
public class StudentDTO {

    @Positive(message = "Student ID must be a positive number")
    private long id;

    @NotBlank(message = "Student name cannot be empty")
    @Size(min = 2, max = 100, message = "Student name must be between 2 and 100 characters")
    private String name;

    @Positive(message = "User ID must be a positive number")
    private long userId;

    @NotEmpty(message = "Student must be enrolled in at least one lesson pack")
    private List<
            @NotNull(message = "Enrolled lesson pack ID cannot be null")
            @Positive(message = "Enrolled lesson pack ID must be a positive number")
                    Long
            > enrolledLessonPackIDs;
}
