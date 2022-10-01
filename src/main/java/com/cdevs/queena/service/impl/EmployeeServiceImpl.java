package com.cdevs.queena.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cdevs.queena.commons.GenericServiceImpl;
import com.cdevs.queena.dao.EmployeeDaoApi;
import com.cdevs.queena.model.Employee;
import com.cdevs.queena.service.api.EmployeeServiceAPI;

@Service
public class EmployeeServiceImpl extends GenericServiceImpl<Employee,Long> implements EmployeeServiceAPI{
    @Autowired
    private EmployeeDaoApi dao;

    @Override
    public CrudRepository<Employee, Long> getDAO() {
        return dao;
    }
    
}