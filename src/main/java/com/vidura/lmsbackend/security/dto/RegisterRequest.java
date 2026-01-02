package com.vidura.lmsbackend.security.dto;

import com.vidura.lmsbackend.security.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotNull
    @NotBlank
    @Email
    private String username;
    @NotNull
    @NotBlank
    @Size(min = 6,max = 20)
    private String password;
    private Role role;
    @Size(min = 6,max = 20)
    private String name;

}
