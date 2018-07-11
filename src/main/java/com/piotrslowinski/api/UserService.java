package com.piotrslowinski.api;

import com.piotrslowinski.model.User;
import com.piotrslowinski.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public void createUser(String firstName, String lastName) {
        userRepository.save(new User(firstName, lastName));
    }

    public void getUser(Long userId) {
        userRepository.findById(userId);
    }

}
