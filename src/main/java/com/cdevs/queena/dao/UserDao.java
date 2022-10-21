package com.cdevs.queena.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdevs.queena.model.User;


@Repository
public interface UserDao extends CrudRepository<User,Long>{
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User getUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.userRole = ?1")
    public List<User> getUserByRole(String role);

    @Query("SELECT u FROM User u WHERE u.dni = ?1")
    public User getUserByDNI(long dni);

    @Query(value ="SELECT * FROM user u JOIN employee_service e ON u.id = e.employee_id WHERE e.service_id IN (:services) GROUP BY(u.id) HAVING COUNT(u.id) >= :size", nativeQuery = true)
    public List<User> findUserByServicesList(@Param("services") List<Integer> services, @Param("size") int size);
}
