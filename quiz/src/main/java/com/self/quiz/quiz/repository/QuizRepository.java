package com.self.quiz.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.self.quiz.quiz.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer>{

}
