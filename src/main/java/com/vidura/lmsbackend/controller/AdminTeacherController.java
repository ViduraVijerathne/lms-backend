package com.vidura.lmsbackend.controller;

import com.vidura.lmsbackend.dto.ServerResponse;
import com.vidura.lmsbackend.dto.TeacherDTO;
import com.vidura.lmsbackend.dto.register.TeacherRegisterDTO;
import com.vidura.lmsbackend.security.dto.AuthResponse;
import com.vidura.lmsbackend.security.dto.RegisterRequest;
import com.vidura.lmsbackend.security.entity.Role;
import com.vidura.lmsbackend.security.service.AuthService;
import com.vidura.lmsbackend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/teachers")
class AdminTeacherController {
    @Autowired
    private AuthService authService;
    @Autowired
    private TeacherService teacherService;

    @PostMapping
    public ResponseEntity<ServerResponse<TeacherDTO>> register(@RequestBody @Validated TeacherRegisterDTO request) {
        ServerResponse<TeacherDTO> serverResponse = new ServerResponse<>();
        try {

            TeacherDTO response = authService.createTeacher(request);

            return   ResponseEntity.ok(serverResponse.fromSuccess(response));
        } catch (Exception e) {
            return ResponseEntity.ok(serverResponse.fromException(e));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ServerResponse<List<TeacherDTO>>> getAllTeachers() {
        ServerResponse<List<TeacherDTO>> serverResponse = new ServerResponse<>();
        try{
          List<TeacherDTO> teachers = teacherService.findAll();
          return   ResponseEntity.ok(serverResponse.fromSuccess(teachers));
        }catch (Exception e){
            return ResponseEntity.ok(serverResponse.fromException(e));
        }
    }
}
