package com.piotrslowinski.model;

public class SurveyUrlAddress implements UrlAddress {

    private final String value;

    public SurveyUrlAddress(Token token, String port, String address) {
        this.value = String.format("http://%s:%s/url/%s", address, port, token.getValue());
    }

    public String getValue() {
        return value;
    }

}
