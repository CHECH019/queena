package com.cdevs.queene.dao;

import org.springframework.data.repository.CrudRepository;

import com.cdevs.queene.model.QService;

public interface MyServiceDaoApi extends CrudRepository<QService,Integer>{
    
}
