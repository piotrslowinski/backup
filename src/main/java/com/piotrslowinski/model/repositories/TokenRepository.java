package com.piotrslowinski.model.repositories;

import com.piotrslowinski.model.Token;

public interface TokenRepository {

    Token get(String tokenValue);

    void save(Token token);
}
