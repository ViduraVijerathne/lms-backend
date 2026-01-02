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
public class TagDTO {

    @Positive(message = "Tag ID must be a positive number")
    private long id;

    @NotBlank(message = "Tag cannot be empty")
    @Size(min = 2, max = 50, message = "Tag name must be between 2 and 50 characters")
    private String tag;

    @Positive(message = "Teacher ID must be a positive number")
    private long teacherID;
}
