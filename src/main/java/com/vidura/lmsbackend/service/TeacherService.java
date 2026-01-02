package com.vidura.lmsbackend.service;

import com.vidura.lmsbackend.dto.TeacherDTO;
import com.vidura.lmsbackend.entity.Teacher;
import com.vidura.lmsbackend.repository.TeacherRepository;
import com.vidura.lmsbackend.security.entity.User;
import com.vidura.lmsbackend.security.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final AuthService authService;

    public TeacherService(TeacherRepository teacherRepository, AuthService authService) {
        this.teacherRepository = teacherRepository;
        this.authService = authService;
    }

    public List<TeacherDTO> findAll() {
        List<TeacherDTO> dtos = new ArrayList<>();
        List<Teacher> teachers = teacherRepository.findAll();
        for (Teacher teacher : teachers) {
            dtos.add(teacher.toDTO());
        }
        return  dtos;
    }

    public Teacher getCurrentTeacher(){
        User u = authService.getCurrentUser();
        if(u == null){
            throw new RuntimeException("no user found");
        }
        Optional<Teacher> teacher = teacherRepository.findTeacherByUser(u);
        if(teacher.isPresent()){
            return teacher.get();
        }else{
            throw new RuntimeException("no teacher found");
        }
    }
}
