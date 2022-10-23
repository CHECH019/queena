package com.cdevs.queene.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cdevs.queene.dao.MyServiceDaoApi;
import com.cdevs.queene.generics.GenericServiceImpl;
import com.cdevs.queene.model.MyService;
import com.cdevs.queene.service.api.MyServiceServiceAPI;

@Service
public class MyServiceServiceImpl extends GenericServiceImpl<MyService,Integer> implements MyServiceServiceAPI{
    @Autowired
    private MyServiceDaoApi dao;

    @Override
    public CrudRepository<MyService, Integer> getDAO() {
        return dao;
    }
    
}