package com.piotrslowinski.ui.controllers;

import com.piotrslowinski.api.SurveyService;
import com.piotrslowinski.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/surveys")
public class SurveyController {


    private SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping
    public void saveSurvey(@RequestParam String name) {
        surveyService.createSurvey(name);
    }

    @GetMapping("/{surveyId}")
    public Survey getSurveyById(@PathVariable Long surveyId) {
        return surveyService.findSurvey(surveyId);
    }

}
