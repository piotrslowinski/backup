package com.piotrslowinski.model.generator;

import com.piotrslowinski.model.Survey;
import com.piotrslowinski.model.Token;
import com.piotrslowinski.model.UrlAddress;
import com.piotrslowinski.model.User;

import javax.persistence.GeneratedValue;

public class UrlGenerator {

    private GenerateAddressStrategy strategy;

    public UrlGenerator(GenerateAddressStrategy strategy) {
        this.strategy = strategy;
    }

    public UrlAddress generate(User user, Survey survey, Token token) {
        return strategy.generate(user, survey, token);
    }
}
