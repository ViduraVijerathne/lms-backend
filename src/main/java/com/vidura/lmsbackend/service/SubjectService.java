package com.vidura.lmsbackend.service;

import com.vidura.lmsbackend.dto.ServerResponse;
import com.vidura.lmsbackend.dto.SubjectDTO;
import com.vidura.lmsbackend.entity.Subject;
import com.vidura.lmsbackend.repository.SubjectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
