package com.self.question.question.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.self.question.question.model.Question;
import com.self.question.question.model.Response;

public interface QuestionRepository extends JpaRepository<Question, Integer>{

	@Query("SELECT q FROM Question q WHERE q.category = :category ORDER BY RAND() Limit 10")
    public List<Question> findByCategory(@Param("category") String category);
	
//
//    @Query("SELECT q FROM Question q WHERE q.category = :category")
//    public Page<Question> findByCategory(String category, Pageable pageable);

	@Query("SELECT q FROM Question q WHERE q.id IN :ids")
	public List<Question> findByIds(@Param("ids") List<Integer> ids);

}
