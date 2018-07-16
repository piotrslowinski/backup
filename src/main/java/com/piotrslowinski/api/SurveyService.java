package com.piotrslowinski.api;

import com.piotrslowinski.model.SourceNotAvailableException;
import com.piotrslowinski.model.Survey;
import com.piotrslowinski.model.Token;
import com.piotrslowinski.model.repositories.SurveyRepository;
import com.piotrslowinski.model.repositories.TokenRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class SurveyService {

    private SurveyRepository surveyRepository;
    private TokenRepository tokenRepository;

    @Autowired
    public SurveyService(SurveyRepository surveyRepository, TokenRepository tokenRepository) {
        this.surveyRepository = surveyRepository;
        this.tokenRepository = tokenRepository;
    }

    public Survey getSurvey(String tokenValue) {
        Token token = getTokenFromRepo(tokenValue);
        try {
            validateToken(token);
        } catch (SourceNotAvailableException e) {
            throw new RuntimeException("out of date",e);
        }

        deactivateToken(token);
        tokenRepository.save(token);
        return token.getSurvey();
    }

    private void validateToken(Token token) throws SourceNotAvailableException {
        if (!token.isValid()) {
            throw new SourceNotAvailableException("the link is not active anymore");
        }
    }

    private Token getTokenFromRepo(String tokenValue) {
        Optional<Token> token = tokenRepository.findByValue(tokenValue);
        if (!token.isPresent()) {
            throw new NoSuchElementException("token not found");
        }
        return token.get();
    }

    private void deactivateToken(Token token) {
        token.setActive(false);
    }

    @Transactional
    public void createSurvey(String name) {
        Survey survey = new Survey(name);
        surveyRepository.save(survey);
    }

    public Survey findSurvey(Long surveyId) {
        return surveyRepository.getOne(surveyId);
    }
}
