package com.vidura.lmsbackend.security.controller;

import com.vidura.lmsbackend.dto.ServerResponse;
import com.vidura.lmsbackend.security.dto.AuthResponse;
import com.vidura.lmsbackend.security.dto.LoginRequest;
import com.vidura.lmsbackend.security.dto.RegisterRequest;
import com.vidura.lmsbackend.security.entity.User;
import com.vidura.lmsbackend.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ServerResponse<AuthResponse>> login(@RequestBody @Validated LoginRequest request) {
        ServerResponse<AuthResponse> serverResponse = new ServerResponse<>();
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(serverResponse.fromSuccess(response));
        } catch (Exception e) {
            return ResponseEntity.ok(serverResponse.fromException(e));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ServerResponse<AuthResponse>> register(@RequestBody @Validated RegisterRequest request) {
        ServerResponse<AuthResponse> serverResponse = new ServerResponse<>();
        try {
            AuthResponse response = authService.register(request);
            return   ResponseEntity.ok(serverResponse.fromSuccess(response));
        } catch (Exception e) {
            return ResponseEntity.ok(serverResponse.fromException(e));
        }
    }



    @GetMapping("/whoami")
    public ResponseEntity<ServerResponse<AuthResponse>> whoami() {
        ServerResponse<AuthResponse> serverResponse = new ServerResponse<>();
        try {
            AuthResponse response = authService.getMe();
            return ResponseEntity.ok(serverResponse.fromSuccess(response));
        } catch (Exception e) {
            return ResponseEntity.ok(serverResponse.fromException(e));
        }
    }


}
