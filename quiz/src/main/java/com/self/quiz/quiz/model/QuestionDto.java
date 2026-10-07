package com.self.quiz.quiz.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
@Data
@JsonPropertyOrder({"id","question","option1","option2","option3","option4"})
public class QuestionDto {

    private int id;

    private String question;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

}