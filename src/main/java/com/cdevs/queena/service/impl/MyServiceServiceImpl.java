package com.cdevs.queena.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cdevs.queena.dao.MyServiceDaoApi;
import com.cdevs.queena.generics.GenericServiceImpl;
import com.cdevs.queena.model.MyService;
import com.cdevs.queena.service.api.MyServiceServiceAPI;

@Service
public class MyServiceServiceImpl extends GenericServiceImpl<MyService,Integer> implements MyServiceServiceAPI{
    @Autowired
    private MyServiceDaoApi dao;

    @Override
    public CrudRepository<MyService, Integer> getDAO() {
        return dao;
    }
    
}