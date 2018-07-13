package com.piotrslowinski.model;

public class LoginUrlAddress implements UrlAddress {


    private final String value;

    public LoginUrlAddress(Token token, String port, String address) {
        this.value = String.format("http://%s:%s/login/%s", address, port, token.getValue());
    }

    public String getValue() {
        return value;
    }
}
