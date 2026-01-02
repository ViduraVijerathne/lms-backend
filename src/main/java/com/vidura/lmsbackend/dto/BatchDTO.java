package com.vidura.lmsbackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchDTO {

    @Positive(message = "Batch ID must be a positive number")
    private long id;

    @NotBlank(message = "Batch name cannot be empty")
    @Size(
            min = 3,
            max = 100,
            message = "Batch name must be between 3 and 100 characters"
    )
    private String name;

    @Positive(message = "Teacher ID must be a positive number")
    private long teacherID;

    @NotBlank(message = "Teacher email cannot be empty")
    @Email(message = "Invalid teacher email format")
    private String teacherEmail;

    @NotNull(message = "Active status must be specified")
    private Boolean isActive;
}
