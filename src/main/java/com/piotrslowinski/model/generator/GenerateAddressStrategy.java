package com.piotrslowinski.model.generator;

import com.piotrslowinski.model.Survey;
import com.piotrslowinski.model.Token;
import com.piotrslowinski.model.UrlAddress;
import com.piotrslowinski.model.User;

public interface GenerateAddressStrategy {

    UrlAddress generate(User user, Survey survey, Token token);
}
