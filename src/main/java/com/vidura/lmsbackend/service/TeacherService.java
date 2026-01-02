package com.vidura.lmsbackend.service;

import com.vidura.lmsbackend.dto.TeacherDTO;
import com.vidura.lmsbackend.entity.Teacher;
import com.vidura.lmsbackend.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<TeacherDTO> findAll() {
        List<TeacherDTO> dtos = new ArrayList<>();
        List<Teacher> teachers = teacherRepository.findAll();
        for (Teacher teacher : teachers) {
            dtos.add(teacher.toDTO());
        }
        return  dtos;
    }
}
