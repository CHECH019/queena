package com.cdevs.queena.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdevs.queena.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client,Long>{
    
}
