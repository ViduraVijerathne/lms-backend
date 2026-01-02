package com.vidura.lmsbackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {

    @Positive(message = "Teacher ID must be a positive number")
    private Long id;

    @NotBlank(message = "Teacher name cannot be empty")
    @Size(min = 2, max = 100, message = "Teacher name must be between 2 and 100 characters")
    private String name;


    private String profileURL;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @Positive(message = "User ID must be a positive number")
    private long userID;

    @Positive(message = "Subject ID must be a positive number")
    private long subjectID;

    private String subjectName;

    @NotEmpty(message = "At least one tag ID is required")
    private List<
            @NotNull(message = "Tag ID cannot be null")
            @Positive(message = "Tag ID must be a positive number")
                    Long
            > tagIDs;
}
