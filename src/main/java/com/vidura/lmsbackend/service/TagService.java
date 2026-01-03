package com.vidura.lmsbackend.service;

import com.vidura.lmsbackend.entity.Tag;
import com.vidura.lmsbackend.entity.Teacher;
import com.vidura.lmsbackend.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag getByNameOrCreateIfNotExist(String tag, Teacher teacher) {
        Optional<Tag> tag1 = tagRepository.findTagByTagAndTeacher(tag,teacher);
        if(tag1.isPresent()) {
            return tag1.get();
        }else{
            Tag t = new Tag();
            t.setTag(tag);
            t.setTeacher(teacher);

            tagRepository.save(t);

            return  t;
        }

    }
}
