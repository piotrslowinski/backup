package com.piotrslowinski.model;

import com.piotrslowinski.model.generator.AbstractUrlGenerator;
import com.piotrslowinski.model.generator.UrlGenerator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class UrlGeneratorTest {

    private AbstractUrlGenerator generator = new UrlGenerator();

    private User user = new User();

    private Survey survey = new Survey();

    private Token token1;
    private Token token2;

    @Test
    public void shouldGenerateUrl() {
        //given
        token1 = createTokenForUser(user, survey);

        //when
        UrlAddress address1 = generator.generateUrlAddress(token1, UrlTargetType.SURVEY);

        //then
        assertFalse(address1.getValue().isEmpty());
    }

    @Test
    public void shouldNotGenerateSameUrlFromDifferentTokens() {
        //given
        token1 = createTokenForUser(user, survey);
        token2 = createTokenForUser(user, survey);

        //when
        UrlAddress address1 = generator.generateUrlAddress(token1, UrlTargetType.SURVEY);
        UrlAddress address2 = generator.generateUrlAddress(token2, UrlTargetType.SURVEY);

        //then
        assertFalse(address1.getValue().equals(address2.getValue()));
    }

    private Token createTokenForUser(User user, Survey survey) {
        Token token = new Token(user, survey);
        user.addToken(token);
        return token;
    }
}
