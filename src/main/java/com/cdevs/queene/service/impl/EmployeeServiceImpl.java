package com.cdevs.queene.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cdevs.queene.dao.EmployeeDaoAPI;
import com.cdevs.queene.model.Employee;
import com.cdevs.queene.service.api.EmployeeServiceAPI;

@Service
public class EmployeeServiceImpl extends GenericServiceImpl<Employee,Long> implements EmployeeServiceAPI {

    @Autowired
    private EmployeeDaoAPI dao;

    @Override
    public CrudRepository<Employee, Long> getDAO() {
        return dao;
    }

    
    
}
