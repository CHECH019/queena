package com.cdevs.queene.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cdevs.queene.dao.MyServiceDaoApi;
import com.cdevs.queene.model.QService;
import com.cdevs.queene.service.api.QServiceServiceAPI;

@Service
public class QServiceServiceImpl extends GenericServiceImpl<QService,Integer> implements QServiceServiceAPI{
    @Autowired
    private MyServiceDaoApi dao;

    @Override
    public CrudRepository<QService, Integer> getDAO() {
        return dao;
    }
    
}