package com.cdevs.queena.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdevs.queena.model.Client;

@Repository
public interface ClientDaoApi extends CrudRepository<Client,Long>{
    
}
