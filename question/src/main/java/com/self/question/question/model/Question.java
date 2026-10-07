package com.self.question.question.model;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@JsonPropertyOrder({"id","category","question","option1","option2","option3","option4","answer"})
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "category cannot be empty")
    private String category;

    @NotBlank(message = "Question cannot be empty")
    @Size(min = 5, max = 200, message = "Provide valid question (5-200 characters)")
    private String question;

    @NotBlank(message = "provide option1")
    private String option1;

    @NotBlank(message = "provide option2")
    private String option2;

    @NotBlank(message = "provide option3")
    private String option3;

    @NotBlank(message = "provide option4")
    private String option4;

    @Min(value = 1, message = "Answer must be at least 1")
    @Max(value = 4, message = "Answer must be at most 4")
    private int answer;

}