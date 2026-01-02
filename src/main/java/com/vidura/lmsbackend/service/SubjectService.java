package com.vidura.lmsbackend.service;

import com.vidura.lmsbackend.dto.ServerResponse;
import com.vidura.lmsbackend.dto.SubjectDTO;
import com.vidura.lmsbackend.entity.Subject;
import com.vidura.lmsbackend.repository.SubjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
class SubjectService {

    private final SubjectRepository subjectRepository;

    SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public ServerResponse<SubjectDTO> createSubject(SubjectDTO subjectDTO) {
        ServerResponse<SubjectDTO> serverResponse = new ServerResponse();
        Subject subject = new Subject();
        subject.setName(subjectDTO.getName());

        subjectRepository.save(subject);
        subjectDTO.setId(subject.getId());
        serverResponse.setData(subjectDTO);
        return serverResponse;
    }

    public ServerResponse<List<SubjectDTO>> getAllSubjects() {
        ServerResponse<List<SubjectDTO>> serverResponse = new ServerResponse<>();
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectDTO> subjectDTOs = subjects.stream()
                .map(Subject::toDTO)
                .toList(); // Java 16+
        serverResponse.setData(subjectDTOs);
        return serverResponse;
    }

    public ServerResponse<SubjectDTO> deactivateSubject(Long id) {
        ServerResponse<SubjectDTO> serverResponse = new ServerResponse<>();
        Optional<Subject> Optsubject =subjectRepository.findById(id);
        if(Optsubject.isPresent()) {
            Subject subject = Optsubject.get();
            SubjectDTO subjectDTO = new SubjectDTO();
            subjectDTO.setId(subject.getId());
            subjectDTO.setName(subject.getName());
            serverResponse.setData(subjectDTO);
            return serverResponse;
        }else{
            throw new RuntimeException("Subject not found with this id");
        }
    }

    public ServerResponse<SubjectDTO> updateSubject(Long id, SubjectDTO subjectDTO) {
        ServerResponse<SubjectDTO> serverResponse = new ServerResponse<>();
        Optional<Subject> Optsubject =subjectRepository.findById(id);
        if(Optsubject.isPresent()) {
            Subject subject = Optsubject.get();
            subject.setName(subjectDTO.getName());
            subjectRepository.save(subject);
            serverResponse.setData(subjectDTO);
            return serverResponse;
        }else{
            throw new RuntimeException("Subject not found with this id");
        }
    }
}
