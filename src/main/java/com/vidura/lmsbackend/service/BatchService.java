package com.vidura.lmsbackend.service;

import com.vidura.lmsbackend.dto.BatchDTO;
import com.vidura.lmsbackend.dto.ServerResponse;
import com.vidura.lmsbackend.dto.register.BatchCreateDTO;
import com.vidura.lmsbackend.entity.Batch;
import com.vidura.lmsbackend.entity.Teacher;
import com.vidura.lmsbackend.repository.BatchRepository;
import com.vidura.lmsbackend.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BatchService {
        private final BatchRepository batchRepository;
        private final TeacherRepository teacherRepository;
    private final TeacherService teacherService;

    BatchService(BatchRepository batchRepository, TeacherRepository teacherRepository, TeacherService teacherService) {
            this.batchRepository = batchRepository;
            this.teacherRepository = teacherRepository;
        this.teacherService = teacherService;
    }

        public ServerResponse<BatchDTO> createBatch(BatchCreateDTO batchDTO) {
            ServerResponse<BatchDTO> response = new ServerResponse<>();
            Batch batch = new Batch();
            batch.setName(batchDTO.getName());
            batch.setTeacher(teacherService.getCurrentTeacher());
            batchRepository.save(batch);
            response.setData(batch.toDTO());
            return response;
        }

        public List<BatchDTO> getAllBatchesByTeacher(Long teacherID) {
        List<BatchDTO> batchDTOS = new ArrayList<>();

        Optional<Teacher> teacher = teacherRepository.findById(teacherID);
        if(teacher.isEmpty()){
            throw new RuntimeException("Teacher not found");
        }

        List<Batch> batch = batchRepository.findBatchesByTeacher(teacher.get());
        for(Batch b : batch){
            batchDTOS.add(b.toDTO());
        }
        return batchDTOS;
        }

        public List<BatchDTO> getCurrentUserBatches(){
        Teacher teacher = teacherService.getCurrentTeacher();
        return getAllBatchesByTeacher(teacher.getId());
        }

        public ServerResponse<BatchDTO> getBatchById(Long id) {
            ServerResponse<BatchDTO> response = new ServerResponse<>();
            Optional<Batch> batch = batchRepository.findById(id);
            if (batch.isPresent()) {
                response.setData(batch.get().toDTO());
                return response;
            }else{
                throw new RuntimeException("Batch not found");
            }
        }

        public ServerResponse<BatchDTO> updateBatch(Long id,BatchDTO batchDTO) {
            ServerResponse<BatchDTO> response = new ServerResponse<>();
            Optional<Batch> batch = batchRepository.findById(id);
            if (batch.isPresent()) {
                Batch batchToUpdate = batch.get();
                batchToUpdate.setName(batchDTO.getName());
                batchToUpdate.setId(id);
                Optional<Teacher> teacher = teacherRepository.findById(batchDTO.getId());
                if (teacher.isPresent()) {
                    batchToUpdate.setTeacher(teacher.get());
                }else{
                    throw new RuntimeException("Teacher not found");
                }
                batchToUpdate.setActive(batchDTO.getIsActive());
                batchRepository.save(batchToUpdate);
                response.setData(batchDTO);
                return response;
            }else{
                throw new RuntimeException("Batch not found");
            }
        }

        public void deleteBatch(Long id) {
            batchRepository.deleteById(id);
        }
}
