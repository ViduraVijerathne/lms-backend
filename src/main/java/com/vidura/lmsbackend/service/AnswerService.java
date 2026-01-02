package com.vidura.lmsbackend.service;

import com.vidura.lmsbackend.dto.AnswerDTO;
import com.vidura.lmsbackend.dto.ServerResponse;
import com.vidura.lmsbackend.entity.Answer;
import com.vidura.lmsbackend.repository.AnswerRepository;
import com.vidura.lmsbackend.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

//    public ServerResponse<AnswerDTO> addAnswer(AnswerDTO answerDTO) {
//        Answer answer = new Answer();
//        answer.setAnswer(answerDTO.getAnswer());
//        answer.setId(answerDTO.getId());
//        try{
//            answer.setQuestion(questionRepository.findById(answerDTO.getId()));
//        }
//    }
}
