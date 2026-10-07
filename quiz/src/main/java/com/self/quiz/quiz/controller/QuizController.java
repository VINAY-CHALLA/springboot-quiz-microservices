package com.self.quiz.quiz.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.self.quiz.quiz.model.Response;
import com.self.quiz.quiz.service.QuizService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("quiz")
public class QuizController {
	private final QuizService qs;
	
	@PostMapping
	public ResponseEntity<String> quize(@RequestBody Map<String,String> request){
		return qs.create(request.get("category"));
	}
	
	@GetMapping("/{quizId}")
	public ResponseEntity<?> quize(@PathVariable int quizId){
		Long start=System.currentTimeMillis();
		ResponseEntity<?> response=qs.getQuiz(quizId);
		Long end=System.currentTimeMillis();
		System.out.println((end-start)+" ms");
		return response;
	}
	
	@PostMapping("/result")
    public ResponseEntity<Integer> result(@RequestBody List<Response> res) {
        return qs.getResult(res);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        return qs.delete(id);
    }

}
