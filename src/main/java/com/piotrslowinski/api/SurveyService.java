package com.piotrslowinski.api;

import com.piotrslowinski.model.Survey;
import com.piotrslowinski.model.Token;
import com.piotrslowinski.model.repositories.SurveyRepository;
import com.piotrslowinski.model.repositories.TokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SurveyService {

    private SurveyRepository surveyRepository;
    private TokenRepository tokenRepository;

    public SurveyService(SurveyRepository surveyRepository, TokenRepository tokenRepository) {
        this.surveyRepository = surveyRepository;
        this.tokenRepository = tokenRepository;
    }


    public Survey getSurvey(String tokenValue) {
        Token token = tokenRepository.get(tokenValue);
        if (!token.isValid()) {
            throw new RuntimeException("the link has expired");
        }
        deactivateToken(token);
        tokenRepository.save(token);
        return token.getSurvey();
    }

    private void deactivateToken(Token token) {
        token.setActive(false);
    }

    @Transactional
    public void createSurvey(String name) {
        Survey survey = new Survey(name);
        surveyRepository.save(survey);
    }
}
