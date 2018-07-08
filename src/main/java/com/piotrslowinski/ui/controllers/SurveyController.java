package com.piotrslowinski.ui.controllers;

import com.piotrslowinski.api.SurveyService;
import com.piotrslowinski.model.Survey;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/surveys")
public class SurveyController {


    private SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping
    public void saveSurvey(@RequestParam String name) {
        surveyService.createSurvey(name);
    }

    @GetMapping("/{tokenValue}")
    public Survey getSurvey(@PathVariable String tokenValue) {

        return surveyService.getSurvey(tokenValue);
    }

}
