package com.cdevs.queena.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import com.cdevs.queena.model.User;


@NoRepositoryBean
public interface UserDao<T extends User> extends CrudRepository<T,Long>{
    @Query("SELECT t from #{#entityName} t WHERE t.email = :email")
    public T getUserByEmail(@Param("email") String email);
}
