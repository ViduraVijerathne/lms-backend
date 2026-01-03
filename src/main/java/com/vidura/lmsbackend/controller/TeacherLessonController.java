package com.vidura.lmsbackend.controller;

import com.vidura.lmsbackend.dto.LessonDTO;
import com.vidura.lmsbackend.dto.ServerResponse;
import com.vidura.lmsbackend.dto.register.LessonRegisterDTO;
import com.vidura.lmsbackend.service.LessonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teacher/lessons")
public class TeacherLessonController {

    private final LessonService lessonService;

    public TeacherLessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping("/create")
    public ResponseEntity<ServerResponse<LessonDTO>> createLesson(@RequestBody LessonRegisterDTO dto){
        ServerResponse<LessonDTO> response  = new ServerResponse<>();
        try{
            LessonDTO lessonDTO = lessonService.create(dto);
            return ResponseEntity.ok(response.fromSuccess(lessonDTO));
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.ok(response.fromException(e));
        }
    }
}
