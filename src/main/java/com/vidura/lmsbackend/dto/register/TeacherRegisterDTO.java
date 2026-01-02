package com.vidura.lmsbackend.dto.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherRegisterDTO {
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    String email;
    @Size(min = 2, max = 100, message = "Teacher name must be between 2 and 100 characters")
    String name;
    @Size(min = 8,max = 50,message = "password should be between 8 an 50")
    String password;
    @Positive(message = "please select subject")
    int subjectID;

}
