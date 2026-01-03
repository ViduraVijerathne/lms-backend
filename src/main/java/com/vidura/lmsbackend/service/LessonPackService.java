package com.vidura.lmsbackend.service;

import com.vidura.lmsbackend.dto.LessonDTO;
import com.vidura.lmsbackend.dto.LessonPackDTO;
import com.vidura.lmsbackend.dto.register.LessonPackRegistrationDTO;
import com.vidura.lmsbackend.entity.Batch;
import com.vidura.lmsbackend.entity.Lesson;
import com.vidura.lmsbackend.entity.LessonPack;
import com.vidura.lmsbackend.entity.Teacher;
import com.vidura.lmsbackend.repository.BatchRepository;
import com.vidura.lmsbackend.repository.LessonPackRepository;
import com.vidura.lmsbackend.repository.LessonRepository;
import org.springframework.stereotype.Service;

import javax.swing.event.ListDataEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LessonPackService {

    private final LessonRepository lessonRepository;
    private final TeacherService teacherService;
    private final BatchRepository batchRepository;
    private final LessonPackRepository lessonPackRepository;

    public LessonPackService(LessonRepository lessonRepository, TeacherService teacherService, BatchRepository batchRepository, LessonPackRepository lessonPackRepository) {
        this.lessonRepository = lessonRepository;
        this.teacherService = teacherService;
        this.batchRepository = batchRepository;
        this.lessonPackRepository = lessonPackRepository;
    }

    public LessonPackDTO create(LessonPackRegistrationDTO dto) {
        Teacher teacher = teacherService.getCurrentTeacher();
       Optional<Batch> batch = batchRepository.findBatchesById(dto.getBatchID());
       if(batch.isEmpty()){
           throw new RuntimeException("Batch not found");
       }
       if(!Objects.equals(batch.get().getTeacher().getId(), teacher.getId())){
           throw new RuntimeException("no access to batch");
       }
        LessonPack lesson = new LessonPack();
        lesson.setName(dto.getName());
        lesson.setDescription(dto.getDescription());
        lesson.setImageURL(dto.getThumbnail());
        lesson.setBatch(batch.get());
        lessonPackRepository.save(lesson);

        return  lesson.toDTO();

    }

    public List<LessonPackDTO> getMyLessonPacks() {
        List<LessonPackDTO> dtos = new ArrayList<>();
         Teacher teacher = teacherService.getCurrentTeacher();
         if(teacher == null){
             throw  new RuntimeException("teacher is null");
         }

         List<Batch> batches = batchRepository.getBatchesByTeacher((teacher));
         List<LessonPack> packs = new ArrayList<>();
         for(Batch batch : batches){
             List<LessonPack> p  = lessonPackRepository.findLessonPacksByBatch(batch);
             packs.addAll(p);
         }

         for(LessonPack p : packs){
             dtos.add(p.toDTO());
         }


        return  dtos;


    }
}
