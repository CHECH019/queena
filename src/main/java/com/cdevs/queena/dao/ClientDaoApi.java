package com.cdevs.queena.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdevs.queena.model.Client;

@Repository
public interface ClientDaoApi extends CrudRepository<Client,Long>{
    @Query(value="SELECT * from client WHERE email = :email", nativeQuery=true)
    public Client getUserByEmail(@Param("email") String email);

}
