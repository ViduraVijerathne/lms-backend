package com.vidura.lmsbackend.service;


import com.vidura.lmsbackend.dto.LessonPackDTO;
import com.vidura.lmsbackend.dto.ServerResponse;
import com.vidura.lmsbackend.entity.Batch;
import com.vidura.lmsbackend.entity.LessonPack;
import com.vidura.lmsbackend.repository.BatchRepository;
import com.vidura.lmsbackend.repository.LessonPackRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class LessonPackService {

    private final LessonPackRepository lessonPackRepository;
    private final BatchRepository batchRepository;

    public LessonPackService(LessonPackRepository lessonPackRepository,BatchRepository batchRepository ) {
        this.lessonPackRepository = lessonPackRepository;
        this.batchRepository = batchRepository;
    }

    public ServerResponse<LessonPackDTO> addLessonPack(LessonPackDTO lessonPackDTO) {
        ServerResponse<LessonPackDTO> lessonPack = new ServerResponse<>();
        LessonPack lessonPackObj = new LessonPack();
        lessonPackObj.setName(lessonPackDTO.getName());
        lessonPackObj.setDescription(lessonPackDTO.getDescription());
        lessonPackObj.setImageURL(lessonPackDTO.getImageURL());
        Optional<Batch> batchObj = batchRepository.findById(lessonPackDTO.getBatchID());
        if(batchObj.isPresent()) {
            lessonPackObj.setBatch(batchObj.get());
        }else{
            throw new RuntimeException("Batch not found");
        }
        lessonPackObj.setActive(true);
        lessonPackRepository.save(lessonPackObj);
        lessonPack.setData(lessonPackDTO);
        return lessonPack;
    }

    public ServerResponse<LessonPackDTO> getLessonPack(Long lessonPackID) {
        ServerResponse<LessonPackDTO> lessonPack = new ServerResponse<>();
        Optional<LessonPack> lessonPackObj = lessonPackRepository.findById(lessonPackID);
        if(lessonPackObj.isPresent()) {
            lessonPack.setData(lessonPackObj.get().toDTO());
            return lessonPack;
        }else{
            throw new RuntimeException("LessonPack not found");
        }
    }

    public ServerResponse<LessonPackDTO> updateLessonPack(Long id,LessonPackDTO lessonPackDTO) {
        ServerResponse<LessonPackDTO> lessonPack = new ServerResponse<>();
        Optional<LessonPack> lessonPackObj = lessonPackRepository.findById(id);
        if(lessonPackObj.isPresent()) {
            lessonPackObj.get().setName(lessonPackDTO.getName());
            lessonPackObj.get().setDescription(lessonPackDTO.getDescription());
            lessonPackObj.get().setImageURL(lessonPackDTO.getImageURL());
            lessonPackObj.get().setActive(lessonPackDTO.getIsActive());
            lessonPackObj.get().setBatch(batchRepository.findById(lessonPackDTO.getBatchID()).get());
            lessonPackObj.get().setId(id);
            lessonPackObj.get().setCreatedAt(lessonPackDTO.getCreatedAt());
            lessonPackRepository.save(lessonPackObj.get());
            lessonPack.setData(lessonPackDTO);
            return lessonPack;
        }else{
            throw new RuntimeException("LessonPack not found");
        }
    }

    public void deleteLessonPack(Long lessonPackID) {
        lessonPackRepository.deleteById(lessonPackID);
    }
}
