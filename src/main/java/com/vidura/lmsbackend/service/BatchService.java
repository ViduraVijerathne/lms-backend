package com.vidura.lmsbackend.service;

import com.vidura.lmsbackend.dto.BatchDTO;
import com.vidura.lmsbackend.dto.ServerResponse;
import com.vidura.lmsbackend.entity.Batch;
import com.vidura.lmsbackend.entity.Teacher;
import com.vidura.lmsbackend.repository.BatchRepository;
import com.vidura.lmsbackend.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class BatchService {
        private final BatchRepository batchRepository;
        private final TeacherRepository teacherRepository;

        public BatchService(BatchRepository batchRepository, TeacherRepository teacherRepository) {
            this.batchRepository = batchRepository;
            this.teacherRepository = teacherRepository;
        }

        public ServerResponse<BatchDTO> createBatch(BatchDTO batchDTO) {
            ServerResponse<BatchDTO> response = new ServerResponse<>();
            Batch batch = new Batch();
            batch.setName(batchDTO.getName());
            Optional<Teacher> teach = teacherRepository.findById(batchDTO.getId());
            if (teach.isPresent()) {
                batch.setTeacher(teach.get());
            }else{
                throw new RuntimeException("Teacher not found");
            }
            batchRepository.save(batch);
            response.setData(batchDTO);
            return response;
        }

        public ServerResponse<List<BatchDTO>> getAllBatches() {
            ServerResponse<List<BatchDTO>> response = new ServerResponse<>();
            List<Batch> batches = batchRepository.findAll();
            List<BatchDTO> batchesDTOs = batches.stream().map(Batch::toDTO).toList();
            response.setData(batchesDTOs);
            return response;
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
