package com.cdevs.queena.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cdevs.queena.commons.GenericServiceImpl;
import com.cdevs.queena.dao.ClientDaoApi;
import com.cdevs.queena.model.Client;
import com.cdevs.queena.service.api.ClientServiceAPI;

@Service
public class ClientServiceImpl extends GenericServiceImpl<Client, Long> implements ClientServiceAPI{
    @Autowired
    private ClientDaoApi dao;

    @Override
    public CrudRepository<Client,Long> getDAO() {
        return dao;
    }
}