package com.vidura.lmsbackend.dto;

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
public class LessonAttachmentDTO {

    @Positive(message = "Attachment ID must be a positive number")
    private long id;

    @Positive(message = "Lesson ID must be a positive number")
    private long lessonId;

    @NotEmpty(message = "At least one attachment link is required")
    @Size(
            max = 20,
            message = "You can add a maximum of 20 attachment links"
    )
    private List<
            @NotNull(message = "Attachment link cannot be null")
            @Pattern(
                    regexp = "^(https?|ftp)://.*$",
                    message = "Each attachment link must be a valid URL"
            )
                    String
            > links;
}
