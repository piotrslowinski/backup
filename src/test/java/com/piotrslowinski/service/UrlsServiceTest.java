package com.piotrslowinski.service;

import com.piotrslowinski.api.UrlService;
import com.piotrslowinski.model.*;
import com.piotrslowinski.model.generator.UrlGenerator;
import com.piotrslowinski.model.repositories.SurveyRepository;
import com.piotrslowinski.model.repositories.TokenRepository;
import com.piotrslowinski.model.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UrlsServiceTest {

    private UrlService service;
    @Mock
    private UserRepository userRepository;
    @Mock
    private SurveyRepository surveyRepository;
    @Mock
    private TokenRepository tokenRepository;

    private UrlGenerator urlGenerator = new UrlGenerator();

    private User user1;

    private Survey survey1;

    @Before
    public void setUp() {
        user1 = new User("Jan", "Nowak");

        survey1 = new Survey("Survey No1");

        service = new UrlService(userRepository, tokenRepository, surveyRepository, urlGenerator);
    }

    @Test
    public void shouldReturnUrl() {
        //given
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user1));
        when(surveyRepository.getOne(1L)).thenReturn(survey1);

        //when
        UrlAddress address = service.createUniqueSurveyUrl(1L, 1L);

        //then
        assertFalse(address.getValue().isEmpty());
    }

    @Test
    public void shouldNotReturnSameUrlTwice() {
        //given
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user1));
        when(surveyRepository.getOne(1L)).thenReturn(survey1);

        //when
        UrlAddress address1 = service.createUniqueSurveyUrl(1L, 1L);
        UrlAddress address2 = service.createUniqueSurveyUrl(1L, 1L);

        //then
        assertFalse(address1.getValue().equals(address2.getValue()));
    }

    @Test
    public void shouldDeactivateOldToken() {
        //given
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user1));
        when(surveyRepository.getOne(1L)).thenReturn(survey1);

        //when
        service.createUniqueSurveyUrl(1L, 1L);
        service.createUniqueSurveyUrl(1L, 1L);

        //then
        List<Token> userActiveTokens = user1.getTokens().stream().filter(Token::isActive)
                .collect(Collectors.toList());
        assertEquals(1, userActiveTokens.size());
        assertEquals(2, user1.getTokens().size());
    }
}
