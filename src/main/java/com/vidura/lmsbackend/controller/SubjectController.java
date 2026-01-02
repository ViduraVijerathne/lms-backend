package com.vidura.lmsbackend.controller;

import com.vidura.lmsbackend.dto.ServerResponse;
import com.vidura.lmsbackend.dto.SubjectDTO;
import com.vidura.lmsbackend.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/subjects")
class SubjectController {
    private final SubjectService subjectService;

    public SubjectController( SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/add")
    public ResponseEntity<ServerResponse<SubjectDTO>> addSubject(@RequestBody SubjectDTO subjectDTO) {
        ServerResponse<SubjectDTO> sub = new ServerResponse<>();
        try{
             sub = subjectService.createSubject(subjectDTO);
            return ResponseEntity.ok(sub);
        }catch (Exception e){
            return ResponseEntity.ok(sub.fromException(e));
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<ServerResponse<List<SubjectDTO>>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @GetMapping("/deactivate")
    public ResponseEntity<ServerResponse<SubjectDTO>> deactivateSubject(@RequestParam Long id){
        ServerResponse<SubjectDTO> sub = new ServerResponse<>();
        try{
            sub = subjectService.deactivateSubject(id);
            return ResponseEntity.ok(sub);
        }catch (Exception e){
            return ResponseEntity.ok(sub.fromException(e));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ServerResponse<SubjectDTO>> updateSubject(@RequestParam Long id ,@RequestBody SubjectDTO subjectDTO) {
        ServerResponse<SubjectDTO> sub = new ServerResponse<>();

        try{
            sub = subjectService.updateSubject(id,subjectDTO);
            return ResponseEntity.ok(sub);
        }catch (Exception e){
            return ResponseEntity.ok(sub.fromException(e));
        }
    }


}
