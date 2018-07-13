package com.piotrslowinski.ui.controllers;

import com.piotrslowinski.api.SurveyService;
import com.piotrslowinski.api.UrlService;
import com.piotrslowinski.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/url")
public class UrlController {

    private SurveyService surveyService;
    private UrlService urlService;

    @Autowired
    public UrlController(SurveyService surveyService, UrlService urlService) {
        this.surveyService = surveyService;
        this.urlService = urlService;
    }

    @GetMapping("/{tokenValue}")
    public Survey getSurvey(@PathVariable String tokenValue) {

        return surveyService.getSurvey(tokenValue);
    }
}
