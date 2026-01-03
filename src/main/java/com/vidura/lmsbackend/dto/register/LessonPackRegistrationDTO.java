package com.vidura.lmsbackend.dto.register;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LessonPackRegistrationDTO {
    @NotBlank(message = "Lesson name cannot be empty")
    @Size(min = 3, max = 100, message = "Lesson name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Lesson description cannot be empty")
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotBlank(message = "Lesson thumbnail URL cannot be empty")
    @Pattern(
            regexp = "^(https?|ftp)://.*$",
            message = "Thumbnail must be a valid URL (http, https, or ftp)"
    )
    private String thumbnail;

    @Positive(message = "please select a batch")
    private Long batchID;


}
