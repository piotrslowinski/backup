package com.piotrslowinski.service;

import com.piotrslowinski.api.UrlService;
import com.piotrslowinski.model.Survey;
import com.piotrslowinski.model.Token;
import com.piotrslowinski.model.UrlAddress;
import com.piotrslowinski.model.User;
import com.piotrslowinski.model.repositories.SurveyRepository;
import com.piotrslowinski.model.repositories.TokenRepository;
import com.piotrslowinski.model.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class UrlsServiceTest {

    private UrlService service;
    private UserRepository userRepository;
    private SurveyRepository surveyRepository;
    private TokenRepository tokenRepository;

    private User user1;

    private Survey survey1;

    @Before
    public void setUp() {
        user1 = new User("Jan", "Nowak");

        survey1 = new Survey("Survey No1");

        userRepository = Mockito.mock(UserRepository.class);
        surveyRepository = Mockito.mock(SurveyRepository.class);
        tokenRepository = Mockito.mock(TokenRepository.class);

        service = new UrlService(userRepository, tokenRepository, surveyRepository);
    }

    @Test
    public void shouldReturnUrl() {
        //given
        Mockito.when(userRepository.findById(1L)).thenReturn(user1);
        Mockito.when(surveyRepository.get(1L)).thenReturn(survey1);

        //when
        UrlAddress address = service.createUniqueSurveyUrl(1L, 1L);

        //then
        assertFalse(address.getValue().isEmpty());
    }

    @Test
    public void shouldNotReturnSameUrlTwice() {
        //given
        Mockito.when(userRepository.findById(1L)).thenReturn(user1);
        Mockito.when(surveyRepository.get(1L)).thenReturn(survey1);

        //when
        UrlAddress address1 = service.createUniqueSurveyUrl(1L, 1L);
        UrlAddress address2 = service.createUniqueSurveyUrl(1L, 1L);

        //then
        assertFalse(address1.getValue().equals(address2.getValue()));
    }

    @Test
    public void shouldDeactivateOldToken() {
        //given
        Mockito.when(userRepository.findById(1L)).thenReturn(user1);
        Mockito.when(surveyRepository.get(1L)).thenReturn(survey1);

        //when
        UrlAddress address1 = service.createUniqueSurveyUrl(1L, 1L);
        UrlAddress address2 = service.createUniqueSurveyUrl(1L, 1L);

        //then
        List<Token> userActiveTokens = user1.getTokens().stream().filter(Token::isActive)
                .collect(Collectors.toList());
        assertEquals(1, userActiveTokens.size());
        assertEquals(2, user1.getTokens().size());
    }
}
