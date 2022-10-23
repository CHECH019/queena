package com.cdevs.queene.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdevs.queene.model.MyService;

@Repository
public interface MyServiceDaoApi extends CrudRepository<MyService,Integer>{
    
}
