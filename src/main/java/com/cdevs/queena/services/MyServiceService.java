package com.cdevs.queena.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdevs.queena.iServices.IMyServiceService;
import com.cdevs.queena.model.MyService;
import com.cdevs.queena.repository.MyServiceRepository;

@Service
public class MyServiceService implements IMyServiceService{
    @Autowired
    private MyServiceRepository repository;

    @Override
    public MyService save(MyService s) {
        return repository.save(s);
    }
    
    @Override
    public List<MyService> getList() {
        return (List<MyService>) repository.findAll();
    }

    @Override
    public MyService getById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public MyService update(MyService s) {
        return repository.save(s);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
    
}