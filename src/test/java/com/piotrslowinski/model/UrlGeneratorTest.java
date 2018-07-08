package com.piotrslowinski.model;

import com.piotrslowinski.model.generator.LocalHostStrategy;
import com.piotrslowinski.model.generator.UrlGenerator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class UrlGeneratorTest {

    private UrlGenerator generator = new UrlGenerator(new LocalHostStrategy());

    private User user = new User();

    private Survey survey = new Survey();

    private Token token1;
    private Token token2;

    @Test
    public void shouldGenerateUrl() {
        //given
        token1 = createTokenForUser(user, survey);

        //when
        UrlAddress address1 = generator.generate(user, survey, token1);

        //then
        assertFalse(address1.getValue().isEmpty());
    }

    @Test
    public void shouldNotGenerateSameUrlFromDifferentTokens() {
        //given
        token1 = createTokenForUser(user, survey);
        token2 = createTokenForUser(user, survey);

        //when
        UrlAddress address1 = generator.generate(user, survey, token1);
        UrlAddress address2 = generator.generate(user, survey, token2);

        //then
        assertFalse(address1.getValue().equals(address2.getValue()));
    }

    private Token createTokenForUser(User user, Survey survey) {
        Token token = new Token(user, survey);
        user.addToken(token);
        return token;
    }
}
