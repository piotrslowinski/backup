package com.piotrslowinski.model.repositories;

import com.piotrslowinski.model.User;

public interface UserRepository {

    User findById(Long id);

    void save(User user);


}
