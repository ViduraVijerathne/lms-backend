package com.vidura.lmsbackend.dto;

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
public class LessonDTO {

    @Positive(message = "Lesson ID must be a positive number")
    private long id;

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

    @Positive(message = "Lesson Pack ID must be a positive number")
    private long lessonPackID;

    @NotEmpty(message = "At least one tag ID is required")
    private List<
            @NotNull(message = "Tag ID cannot be null")
            @Positive(message = "Tag ID must be a positive number")
                    Long
            > tagIDs;
}
