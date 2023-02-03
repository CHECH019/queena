package com.cdevs.queene.qservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cdevs.queene.generics.GenericServiceImpl;

@Service
public class QServiceService extends GenericServiceImpl<QService,Integer> implements iQServiceService{
    @Autowired
    private QServiceDao dao;

    @Override
    public CrudRepository<QService, Integer> getDAO() {
        return dao;
    }

    @Override
    public QService update(QService update) {
        // TODO Auto-generated method stub
        return null;
    }
    
}