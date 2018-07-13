package com.piotrslowinski.api;

import com.piotrslowinski.model.*;
import com.piotrslowinski.model.generator.AbstractUrlGenerator;
import com.piotrslowinski.model.UrlTargetType;
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

    private AbstractUrlGenerator urlGenerator;

    @Autowired
    public UrlService(UserRepository userRepository, TokenRepository tokenRepository,
                      SurveyRepository surveyRepository, AbstractUrlGenerator urlGenerator) {
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

        return generate(token);
    }

    private void synchronizeUserTokens(User user) {
        if (user.hasTokens()){
            deactivateUsersTokens(user);
        }
    }

    private void deactivateUsersTokens(User user) {
        user.getTokens().stream().forEach((token -> token.setActive(false)));
    }

    private UrlAddress generate(Token token) {
        return urlGenerator.generateUrlAddress(token, UrlTargetType.SURVEY);
    }
}
