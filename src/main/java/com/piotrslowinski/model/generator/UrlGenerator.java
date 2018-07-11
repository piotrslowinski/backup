package com.piotrslowinski.model.generator;

import com.piotrslowinski.model.Survey;
import com.piotrslowinski.model.Token;
import com.piotrslowinski.model.UrlAddress;
import com.piotrslowinski.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlGenerator {

    @Value("${server.port}")
    private String port;

    @Value("${server.address}")
    private String address;


    public UrlAddress generate(User user, Survey survey, Token token) {
        String url = String.format("http://%s:%s/url/%s", address, port, token.getValue());

        return new UrlAddress(url);
    }

    public String getUrl() {
        String url = String.format("http://%s:%s/url/", address, port);
        return url;
    }
}
