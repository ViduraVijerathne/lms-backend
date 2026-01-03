package com.vidura.lmsbackend.service;

import com.vidura.lmsbackend.dto.LessonDTO;
import com.vidura.lmsbackend.dto.register.LessonRegisterDTO;
import com.vidura.lmsbackend.entity.Lesson;
import com.vidura.lmsbackend.entity.LessonPack;
import com.vidura.lmsbackend.entity.Tag;
import com.vidura.lmsbackend.entity.Teacher;
import com.vidura.lmsbackend.repository.LessonPackRepository;
import com.vidura.lmsbackend.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public  class LessonService {
    private final TeacherService teacherService;
    private final LessonPackRepository lessonPackRepository;
    private final TagService tagService;
    private final LessonRepository lessonRepository;

    public LessonService(TeacherService teacherService, LessonPackRepository lessonPackRepository, TagService tagService,LessonRepository lessonRepository) {
        this.teacherService = teacherService;
        this.lessonPackRepository = lessonPackRepository;
        this.tagService = tagService;
        this.lessonRepository = lessonRepository;
    }

    public LessonDTO create(LessonRegisterDTO dto) {
        Teacher teacher = teacherService.getCurrentTeacher();
        if(teacher == null){
            throw new RuntimeException("teacher is null");
        }

        Optional<LessonPack> lessonPack = lessonPackRepository.findLessonPacksById(dto.getLessonPackID());
        if(lessonPack.isEmpty()){
            throw new RuntimeException("lessonPack is null");
        }
        if(lessonPack.get().getBatch().getTeacher().getId() != teacher.getId()){
            throw new RuntimeException("teacher id not match");
        }
        List<Tag> tags = new ArrayList<>();
        for(String tag:dto.getTags()){
            Tag    t = tagService.getByNameOrCreateIfNotExist(tag,teacher);
            tags.add(t);
        }

        Lesson lesson = new Lesson();
        lesson.setTags(tags);
        lesson.setName(dto.getName());
        lesson.setDescription(dto.getDescription());
        lesson.setThumbnail(dto.getThumbnail());
        lesson.setLessonPack(lessonPack.get());

        lessonRepository.save(lesson);

        return  lesson.toDTO();

    }
}
