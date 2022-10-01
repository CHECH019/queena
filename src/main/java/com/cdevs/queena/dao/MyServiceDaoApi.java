package com.cdevs.queena.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdevs.queena.model.MyService;

@Repository
public interface MyServiceDaoApi extends CrudRepository<MyService,Integer>{
    
}
