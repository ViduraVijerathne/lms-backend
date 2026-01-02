package com.vidura.lmsbackend.service;

import com.vidura.lmsbackend.dto.AnswerDTO;
import com.vidura.lmsbackend.dto.ServerResponse;
import com.vidura.lmsbackend.entity.Answer;
import com.vidura.lmsbackend.entity.Question;
import com.vidura.lmsbackend.repository.AnswerRepository;
import com.vidura.lmsbackend.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    public ServerResponse<AnswerDTO> addAnswer(AnswerDTO answerDTO) {
        ServerResponse<AnswerDTO> response = new ServerResponse<>();
        Answer answer = new Answer();
        answer.setAnswer(answerDTO.getAnswer());
        answer.setId(answerDTO.getId());
        Optional<Question> question = questionRepository.findById(answerDTO.getId());
            if(question.isPresent()) {
                answer.setQuestion(question.get());
            }else{
                throw new RuntimeException("Question not found");
            }
        answerRepository.save(answer);
            response.setData(answerDTO);
            return response;
   }

   public ServerResponse<AnswerDTO> updateAnswer(Long id,AnswerDTO answerDTO) {
        Optional<Answer> answer = answerRepository.findById(id);
        if(answer.isPresent()) {
            ServerResponse<AnswerDTO> response = new ServerResponse<>();
            Answer ans = new Answer();
            ans.setAnswer(answerDTO.getAnswer());
            ans.setId(answerDTO.getId());
            Optional<Question> question = questionRepository.findById(answerDTO.getId());
            if(question.isPresent()) {
                ans.setQuestion(question.get());
            }else{
                throw new RuntimeException("Question not found");
            }
            answerRepository.save(ans);
            response.setData(answerDTO);
            return response;
        }else{
            throw new RuntimeException("Answer not found");
        }
   }

   public ServerResponse<AnswerDTO> getAnswer(Long id) {
        ServerResponse<AnswerDTO> response = new ServerResponse<>();
        Optional<Answer> answer = answerRepository.findById(id);
        if(answer.isPresent()) {
            AnswerDTO answerDTO = new AnswerDTO();
            answerDTO.setAnswer(answer.get().getAnswer());
            answerDTO.setId(answer.get().getId());
            answerDTO.setQuestionID(answer.get().getQuestion().getId());
            response.setData(answerDTO);
            return response;
        }else{
            throw new RuntimeException("Answer not found");
        }
   }

   public void deleteAnswer(Long id) {
        Optional<Answer> answer = answerRepository.findById(id);
        if(answer.isPresent()) {
            answerRepository.deleteById(id);
        }else{
            throw new RuntimeException("Answer not found");
        }
   }
}
