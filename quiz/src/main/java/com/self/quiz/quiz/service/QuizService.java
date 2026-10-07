package com.self.quiz.quiz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.self.quiz.quiz.Feign.QuestionFeign;
import com.self.quiz.quiz.model.QuestionDto;
import com.self.quiz.quiz.model.Quiz;
import com.self.quiz.quiz.model.Response;
import com.self.quiz.quiz.repository.QuizRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuizService {

	private final QuestionFeign qf;
	private final QuizRepository qr;

	public ResponseEntity<String> create(String category) {
		ResponseEntity<List<Integer>> questions=qf.questions(category);
		System.out.println(questions);
		if(questions.getBody().size()!=0) {
			Quiz quiz=new Quiz();
			quiz.setCategory(category);
			quiz.setQuestion(questions.getBody());
			quiz=qr.save(quiz);
			return new ResponseEntity<>("Quize sucessfully created with ID : "+quiz.getId(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("We are feeling bad, unable to create Quize for mentioned category",HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	public ResponseEntity<?> getQuiz(int id){
        Quiz quiz=qr.findById(id).orElse(null);
        if(quiz!=null){
            List<QuestionDto> questiondto=qf.questions(quiz.getQuestion()).getBody();
            return new ResponseEntity<>(questiondto,HttpStatus.OK);
        }
        return new ResponseEntity<>("No Quize with ID "+id,HttpStatus.NO_CONTENT);
    }

	public ResponseEntity<Integer> getResult(List<Response> res){
        return qf.score(res);
    }
	
	public ResponseEntity<String> delete(int id){
        Quiz result=qr.findById(id).orElse(null);
        if(result!=null){
            result.getQuestion().clear();
            qr.save(result);
            qr.deleteById(id);
            return new ResponseEntity<>("Successfully deleted Quiz with ID: "+id,HttpStatus.OK);
        }
        return new ResponseEntity<>("No Quiz found with Id "+id,HttpStatus.NO_CONTENT);
    }

}
