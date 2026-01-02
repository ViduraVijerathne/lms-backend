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
public class SubjectDTO {

    @Positive(message = "Subject ID must be a positive number")
    private long id;

    @NotBlank(message = "Subject name cannot be empty")
    @Size(min = 2, max = 100, message = "Subject name must be between 2 and 100 characters")
    private String name;
}
