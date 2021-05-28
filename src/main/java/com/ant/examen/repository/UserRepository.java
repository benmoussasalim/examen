package com.ant.examen.repository;

import com.ant.examen.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer > {
    boolean existsByEmail(String email);

    Optional<User> findOneByEmail(String username);
}
