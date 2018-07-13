package com.piotrslowinski.model.repositories;

import com.piotrslowinski.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {

    Optional<Token> findByValue(String value);
}
