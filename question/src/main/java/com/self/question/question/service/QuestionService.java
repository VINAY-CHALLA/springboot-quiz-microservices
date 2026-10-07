package com.self.question.question.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.self.question.question.model.Question;
import com.self.question.question.model.QuestionDto;
import com.self.question.question.model.Response;
import com.self.question.question.repository.QuestionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionService {

	private final QuestionRepository qr;

	public ResponseEntity<List<Question>> add(List<Question> q) {
		List<Question> question=qr.saveAll(q);
		return new ResponseEntity<>(question,HttpStatus.CREATED);
	}


	public Page<Question> getQuestions(Pageable pageable) {
		return qr.findAll(pageable);
	}
	public ResponseEntity<List<Integer>> getQuestionsByCategory(String category) {
		List<Question> questions= qr.findByCategory(category);
		List<Integer> questionIds=questions.stream()
				.map(question-> question.getId())
				.toList();
		return new ResponseEntity<>(questionIds,HttpStatus.OK);
	}
	//	public Page<Question> getQuestionsByCategory(String category,Pageable pageable) {
	//		return qr.findByCategory(category,pageable);
	//	}
	
	@Async
	public CompletableFuture<QuestionDto> multhithreadMapping(Question q){
		QuestionDto questionDto=new QuestionDto(q.getId(), q.getQuestion(), 
	            q.getOption1(), q.getOption2(), 
	            q.getOption3(), q.getOption4());
		return CompletableFuture.completedFuture(questionDto);
	}
	public ResponseEntity<List<QuestionDto>> getQuestionsByCategory(List<Integer> questionIds) {
		List<Question> questions=qr.findAllById(questionIds);
//		List<QuestionDto> questionDto = questions.stream()
//							    .map(q -> new QuestionDto(q.getId(), q.getQuestion(), 
//							            q.getOption1(), q.getOption2(), 
//							            q.getOption3(), q.getOption4()))
//							    .toList();
		List<CompletableFuture<QuestionDto>> questionDto=new ArrayList<>();
		for(Question que:questions) {
			questionDto.add(multhithreadMapping(que));
		}
		// ✅ Wait for all futures to complete and collect results
	    List<QuestionDto> questionDtos = questionDto.stream()
	            .map(CompletableFuture::join)  // blocks until each future completes
	            .toList();

	    return new ResponseEntity<>(questionDtos, HttpStatus.OK);
	}
	
	public ResponseEntity<Integer> getScore(List<Response> response) {
		Map<Integer, Integer> responseMap = response.stream()
				.collect(Collectors.toMap(
						Response::getQuestionId, 
						Response::getAnswer
						));

		int score= (int) qr.findByIds(new ArrayList<>(responseMap.keySet()))
				.stream()
				.filter(q -> responseMap.getOrDefault(q.getId(), -1) == q.getAnswer())
				.count();
		return new ResponseEntity<>(score,HttpStatus.OK);
	}
	public ResponseEntity<?> update(Question question){
		if(qr.existsById(question.getId())){
			Question q=qr.save(question);
			return new ResponseEntity<>(q,HttpStatus.OK);
		}
		return new ResponseEntity<>("No Question found with Id "+question.getId(),HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<String> delete(int id){
		Question result=qr.findById(id).orElse(null);
		if(result!=null){
			qr.deleteById(id);
			return new ResponseEntity<>("Successfully deleted Question with ID: "+id,HttpStatus.OK);
		}
		return new ResponseEntity<>("No Question found with Id "+id,HttpStatus.NO_CONTENT);
	}


	

}
