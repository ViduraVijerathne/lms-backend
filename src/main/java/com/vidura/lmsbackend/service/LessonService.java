package com.vidura.lmsbackend.service;

import com.vidura.lmsbackend.dto.LessonDTO;
import com.vidura.lmsbackend.dto.ServerResponse;
import com.vidura.lmsbackend.entity.Lesson;
import com.vidura.lmsbackend.entity.LessonPack;
import com.vidura.lmsbackend.repository.LessonPackRepository;
import com.vidura.lmsbackend.repository.LessonRepository;
import com.vidura.lmsbackend.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class LessonService {
    private final LessonRepository lessonRepository;
    private final LessonPackRepository lessonPackRepository;
    private final TagRepository tagRepository;

    public LessonService(LessonRepository lessonRepository, LessonPackRepository lessonPackRepository, TagRepository tagRepository) {
        this.lessonRepository = lessonRepository;
        this.lessonPackRepository = lessonPackRepository;
        this.tagRepository = tagRepository;
    }

    public ServerResponse<LessonDTO> addLesson(LessonDTO lessonDTO) {
        ServerResponse<LessonDTO> response = new ServerResponse<>();
        Lesson lesson = new Lesson();
        lesson.setName(lessonDTO.getName());
        lesson.setDescription(lessonDTO.getDescription());
        lesson.setThumbnail(lessonDTO.getThumbnail());
        Optional<LessonPack> lessonPack= lessonPackRepository.findById(lessonDTO.getLessonPackID());
        if(lessonPack.isPresent()) {
            lesson.setLessonPack(lessonPack.get());
        }else{
            throw new RuntimeException("LessonPack not found");
        }
        lesson.setTags(lessonDTO.getTagIDs().stream().map(tagID -> tagRepository.findById(tagID).get()).toList());
        lessonRepository.save(lesson);
        response.setData(lessonDTO);
        return response;
    }

    public ServerResponse<LessonDTO> updateLesson(Long id,LessonDTO lessonDTO) {
        ServerResponse<LessonDTO> response = new ServerResponse<>();
        Optional<Lesson> lesson = lessonRepository.findById(id);
        if(lesson.isPresent()) {
            Lesson updatedLesson = lesson.get();
           updatedLesson.setName(lessonDTO.getName());
           updatedLesson.setDescription(lessonDTO.getDescription());
           updatedLesson.setThumbnail(lessonDTO.getThumbnail());
           updatedLesson.setLessonPack(lessonPackRepository.findById(lessonDTO.getLessonPackID()).get());
           updatedLesson.setTags(lessonDTO.getTagIDs().stream().map(tagID -> tagRepository.findById(tagID).get()).toList());
           lessonRepository.save(updatedLesson);
           response.setData(lessonDTO);
           return response;
        }else{
            throw new RuntimeException("Lesson not found");
        }
    }

    public ServerResponse<LessonDTO> getLesson(Long id) {
        ServerResponse<LessonDTO> response = new ServerResponse<>();
        Lesson lesson = lessonRepository.findById(id).get();
        response.setData(lesson.toDTO());
        return response;
    }

    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }
}
