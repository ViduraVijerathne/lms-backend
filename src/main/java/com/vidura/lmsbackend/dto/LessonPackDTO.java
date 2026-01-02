package com.vidura.lmsbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonPackDTO {

    @Positive(message = "Lesson Pack ID must be a positive number")
    private Long id;

    @NotBlank(message = "Lesson pack name cannot be empty")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Lesson pack description cannot be empty")
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotBlank(message = "Image URL cannot be empty")
    private String imageURL;

    @NotNull(message = "Creation date must be provided")
    private LocalDateTime createdAt;

    @Positive(message = "Batch ID must be a positive number")
    private long batchID;

    @NotNull(message = "Active status must be specified")
    private Boolean isActive;
}
