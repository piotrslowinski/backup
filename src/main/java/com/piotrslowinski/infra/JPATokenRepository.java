package com.piotrslowinski.infra;

import com.piotrslowinski.model.Token;
import com.piotrslowinski.model.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
public class JPATokenRepository implements TokenRepository {

    @Autowired
    private EntityManager entityManager;

    public JPATokenRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Token get(String tokenValue) {
        Token token =  entityManager.find(Token.class, tokenValue);
        if (token == null)
            throw new NoSuchEntityException();
        return token;
    }

    @Override
    @Transactional
    public void save(Token token) {
        entityManager.persist(token);
    }
}
