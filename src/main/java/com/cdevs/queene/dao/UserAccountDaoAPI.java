package com.cdevs.queene.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdevs.queene.model.UserAccount;

public interface UserAccountDaoAPI extends JpaRepository<UserAccount,Long>{
    public Optional<UserAccount> findByEmail(String email);
}
