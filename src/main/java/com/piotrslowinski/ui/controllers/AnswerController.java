package com.piotrslowinski.ui.controllers;

import com.piotrslowinski.model.Answer;
import com.piotrslowinski.model.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    private AnswerRepository answerRepository;

    @Autowired
    public AnswerController(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @GetMapping("/{answerId}")
    public Answer getAnswer(@PathVariable Long answerId) {
        return answerRepository.findById(answerId).get();
    }
}
