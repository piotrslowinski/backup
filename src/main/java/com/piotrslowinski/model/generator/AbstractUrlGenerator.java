package com.piotrslowinski.model.generator;

import com.piotrslowinski.model.Token;
import com.piotrslowinski.model.UrlAddress;
import com.piotrslowinski.model.UrlTargetType;


public interface AbstractUrlGenerator {

    UrlAddress generateUrlAddress(Token token, UrlTargetType type);

}
