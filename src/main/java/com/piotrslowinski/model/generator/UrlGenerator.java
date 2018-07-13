package com.piotrslowinski.model.generator;

import com.piotrslowinski.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlGenerator implements AbstractUrlGenerator {

    @Value("${server.port}")
    private String port;

    @Value("${server.address}")
    private String address;

    public UrlAddress generateUrlAddress(Token token, UrlTargetType type) {
        switch (type) {
            case SURVEY:
                return new SurveyUrlAddress(token, port, address);
            case LOGIN:
                return new LoginUrlAddress(token, port, address);
            default:
                return null;
        }
    }

}
