package com.cdevs.queena.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdevs.queena.model.User;


@Repository
public interface UserDao extends CrudRepository<User,Long>{
    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User getUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.userRole = :role")
    public List<User> getUserByRole(@Param("role") String role);

    @Query("SELECT u FROM User u WHERE u.dni = :dni")
    public User getUserByDNI(@Param("dni") long dni);
}
