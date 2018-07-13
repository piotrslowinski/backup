package com.piotrslowinski.api;

import com.piotrslowinski.model.*;
import com.piotrslowinski.model.generator.UrlGenerator;
import com.piotrslowinski.model.repositories.SurveyRepository;
import com.piotrslowinski.model.repositories.TokenRepository;
import com.piotrslowinski.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UrlService {

    private UserRepository userRepository;

    private TokenRepository tokenRepository;

    private SurveyRepository surveyRepository;

    private UrlGenerator urlGenerator;

    @Autowired
    public UrlService(UserRepository userRepository, TokenRepository tokenRepository, SurveyRepository surveyRepository, UrlGenerator urlGenerator) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.surveyRepository = surveyRepository;
        this.urlGenerator = urlGenerator;
    }

    public UrlAddress createUniqueSurveyUrl(Long userId, Long surveyId) {
        User user = userRepository.findById(userId).get();
        Survey survey = surveyRepository.getOne(surveyId);
        synchronizeUserTokens(user);
        Token token = new Token(user, survey);
        user.addToken(token);
        userRepository.save(user);
        tokenRepository.save(token);

        return generate(user, survey, token);
    }

    private void synchronizeUserTokens(User user) {
        if (user.hasTokens()){
            deactivateUsersTokens(user);
        }
    }

    private void deactivateUsersTokens(User user) {
        user.getTokens().stream().forEach((token -> token.setActive(false)));
    }

    private UrlAddress generate(User user, Survey survey, Token token) {
        return urlGenerator.generate(user, survey, token);
    }

    public String getUrl() {
        return urlGenerator.getUrl();
    }
}
