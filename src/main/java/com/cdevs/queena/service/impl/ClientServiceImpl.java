package com.cdevs.queena.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdevs.queena.dao.ClientDaoApi;
import com.cdevs.queena.dao.UserDao;
import com.cdevs.queena.model.Client;
import com.cdevs.queena.service.api.ClientServiceAPI;

@Service
@Transactional
public class ClientServiceImpl extends UserServiceImpl<Client> implements ClientServiceAPI{

    @Autowired
    private ClientDaoApi dao;

    @Override
    public UserDao<Client> getDAO() {
        return dao;
    }
    
}