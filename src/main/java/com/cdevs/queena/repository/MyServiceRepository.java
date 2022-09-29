package com.cdevs.queena.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdevs.queena.model.MyService;

@Repository
public interface MyServiceRepository extends CrudRepository<MyService,Integer>{
    
}
