package com.piotrslowinski.infra;

import com.piotrslowinski.model.User;
import com.piotrslowinski.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Component
public class JPAUserRepository implements UserRepository {

    @Autowired
    private EntityManager entityManager;

    public JPAUserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public User findById(Long id) {
        User user = entityManager.find(User.class, id);
        if (user == null)
            throw new NoSuchEntityException();
        return user;
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }
}
