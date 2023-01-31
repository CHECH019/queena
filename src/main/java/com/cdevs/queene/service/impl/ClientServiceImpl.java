package com.cdevs.queene.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cdevs.queene.dao.ClientDaoAPI;
import com.cdevs.queene.model.Client;
import com.cdevs.queene.service.api.ClientServiceAPI;

@Service
public class ClientServiceImpl extends GenericServiceImpl<Client,Long> implements ClientServiceAPI{
    
    @Autowired
    private ClientDaoAPI dao;
    
    @Override
    public CrudRepository<Client, Long> getDAO() {
        return dao;
    }

    
    
}
