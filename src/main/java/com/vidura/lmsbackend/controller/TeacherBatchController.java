package com.vidura.lmsbackend.controller;

import com.vidura.lmsbackend.dto.BatchDTO;
import com.vidura.lmsbackend.dto.ServerResponse;
import com.vidura.lmsbackend.dto.register.BatchCreateDTO;
import com.vidura.lmsbackend.entity.Batch;
import com.vidura.lmsbackend.service.BatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher/batches")
public class TeacherBatchController {

    private final BatchService batchService;

    public TeacherBatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping
    public ResponseEntity<ServerResponse<BatchDTO>> createBatch(@RequestBody @Validated BatchCreateDTO batchDTO){
        ServerResponse<BatchDTO> response = new ServerResponse<>();
        try{
            ServerResponse<BatchDTO> resp  = batchService.createBatch(batchDTO);
            return ResponseEntity.ok(resp);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(response.fromException(e));
        }
    }

    @GetMapping
    public ResponseEntity<ServerResponse<List<BatchDTO>>> getAllBatches(){
        ServerResponse<List<BatchDTO>> response = new ServerResponse<>();
        try{
            List<BatchDTO> dtos = batchService.getCurrentUserBatches();
            response.fromSuccess(dtos);
            return  ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.ok(response.fromException(e));
        }
    }
}
