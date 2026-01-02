package com.vidura.lmsbackend.dto.register;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BatchCreateDTO {
    @NotBlank(message = "Batch name cannot be empty")
    @Size(
            min = 3,
            max = 100,
            message = "Batch name must be between 3 and 100 characters"
    )
    private String name;
}
