package com.cdevs.queene.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdevs.queene.model.Client;

public interface ClientDaoAPI extends JpaRepository<Client,Long>{
    
}
