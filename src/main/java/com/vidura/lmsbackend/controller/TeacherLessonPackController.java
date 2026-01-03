package com.vidura.lmsbackend.controller;

import com.vidura.lmsbackend.dto.LessonDTO;
import com.vidura.lmsbackend.dto.LessonPackDTO;
import com.vidura.lmsbackend.dto.ServerResponse;
import com.vidura.lmsbackend.dto.register.LessonPackRegistrationDTO;
import com.vidura.lmsbackend.service.LessonPackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/teacher/lessonpacks")
public class TeacherLessonPackController {

    private final LessonPackService lessonPackService;

    public TeacherLessonPackController(LessonPackService lessonPackService) {
        this.lessonPackService = lessonPackService;
    }

    @PostMapping("/create")
    public ResponseEntity<ServerResponse<LessonPackDTO>> create(@Validated @RequestBody LessonPackRegistrationDTO dto) {
        ServerResponse<LessonPackDTO> response = new ServerResponse<>();
        System.out.println("its works");
        try{
            LessonPackDTO lessonDTO = lessonPackService.create(dto);
            return  ResponseEntity.ok(response.fromSuccess(lessonDTO));
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.ok(response.fromException(e));
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<ServerResponse<List<LessonPackDTO>>> getall(){
        ServerResponse<List<LessonPackDTO>> response = new ServerResponse<>();
        try{
            List<LessonPackDTO> dtos = lessonPackService.getMyLessonPacks();
            return  ResponseEntity.ok(response.fromSuccess(dtos));
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.ok(response.fromException(e));
        }
    }
}
