package com.vidura.lmsbackend.controller;

import com.vidura.lmsbackend.dto.ServerResponse;
import com.vidura.lmsbackend.security.dto.AuthResponse;
import com.vidura.lmsbackend.security.dto.RegisterRequest;
import com.vidura.lmsbackend.security.entity.Role;
import com.vidura.lmsbackend.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/teachers")
class AdminTeacherController {
    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<ServerResponse<AuthResponse>> register(@RequestBody @Validated RegisterRequest request) {
        ServerResponse<AuthResponse> serverResponse = new ServerResponse<>();
        try {
            if(request.getRole() != Role.TEACHER) {
                throw new RuntimeException("invalid role");
            }
            AuthResponse response = authService.register(request);
            return   ResponseEntity.ok(serverResponse.fromSuccess(response));
        } catch (Exception e) {
            return ResponseEntity.ok(serverResponse.fromException(e));
        }
    }
}
