package com.self.quiz.quiz.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.self.quiz.quiz.model.QuestionDto;
import com.self.quiz.quiz.model.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuestionFeign {
	
	@GetMapping("/questions/{category}")
    public ResponseEntity<List<Integer>> questions(@PathVariable String category);

    @PostMapping("/questions")
    public ResponseEntity<List<QuestionDto>> questions(@RequestBody List<Integer> questionIds);

    @PostMapping("/questions/results")
    public ResponseEntity<Integer> score(@RequestBody List<Response> answers);

}
