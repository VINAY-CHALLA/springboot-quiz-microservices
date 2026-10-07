package com.self.question.question.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonPropertyOrder({"id","question","option1","option2","option3","option4"})
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    private int id;

    private String question;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

}
