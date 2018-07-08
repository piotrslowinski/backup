package com.piotrslowinski.model.generator;

import com.piotrslowinski.model.Survey;
import com.piotrslowinski.model.Token;
import com.piotrslowinski.model.UrlAddress;
import com.piotrslowinski.model.User;
import com.piotrslowinski.model.generator.GenerateAddressStrategy;

public class LocalHostStrategy implements GenerateAddressStrategy {


    @Override
    public UrlAddress generate(User user, Survey survey, Token token) {
        StringBuilder url = new StringBuilder();
        url.append("http://localhost:8080/surveys/").
                append(token.getValue());

        return new UrlAddress(url.toString());
    }
}
