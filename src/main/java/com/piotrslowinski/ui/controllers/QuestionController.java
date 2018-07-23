package com.piotrslowinski.ui.controllers;

import com.piotrslowinski.model.Question;
import com.piotrslowinski.model.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("/{questionId}")
    public Question getQuestion(@PathVariable Long questionId) {
        return questionRepository.findById(questionId).get();
    }
}
