package com.cdevs.queene.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long>{
    public Optional<User> findByEmail(String email);
}
