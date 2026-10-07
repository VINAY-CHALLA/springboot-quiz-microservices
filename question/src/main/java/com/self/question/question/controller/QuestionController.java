package com.self.question.question.controller;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.self.question.question.model.Question;
import com.self.question.question.model.QuestionDto;
import com.self.question.question.model.Response;
import com.self.question.question.service.QuestionService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/questions")
@RestController
@AllArgsConstructor
public class QuestionController {
	
	private final QuestionService qs;
	
	@PostMapping("add")
    public ResponseEntity<List<Question>> question( @Valid @RequestBody List<Question> question){
        return qs.add(question);
    }
	
	@GetMapping
	public Page<Question> questions(Pageable pageable){
		return qs.getQuestions(pageable);
	}
	
	@GetMapping("/{category}")
	public ResponseEntity<List<Integer>> questions(@PathVariable String category){
		System.out.println(category);
		return qs.getQuestionsByCategory(category);
	}
	
	@PostMapping()
	public ResponseEntity<List<QuestionDto>> questions(@RequestBody List<Integer> questionIds){
		return qs.getQuestionsByCategory(questionIds);
	}
	
	@PostMapping("/results")
	public ResponseEntity<Integer> score(@RequestBody List<Response> answers){
		 return qs.getScore(answers);
	}
	
	@PutMapping
    public ResponseEntity<?> question(@RequestBody Question question){
        return qs.update(question);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        return qs.delete(id);
    }
	

}
