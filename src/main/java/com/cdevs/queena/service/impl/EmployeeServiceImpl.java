package com.cdevs.queena.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdevs.queena.dao.EmployeeDaoApi;
import com.cdevs.queena.dao.UserDao;
import com.cdevs.queena.exceptions.QAuthException;
import com.cdevs.queena.model.Employee;
import com.cdevs.queena.service.api.EmployeeServiceAPI;

@Service
public class EmployeeServiceImpl extends UserServiceImpl<Employee> implements EmployeeServiceAPI{
    @Autowired
    private EmployeeDaoApi dao;

    @Override
    public Employee getByDni(Long id) {
        return dao.getEmployeeByDni(id);
    }

    @Override
    public Employee save(Employee entity) {
        if(dao.getEmployeeByDni(entity.getDni())!= null){
            throw new QAuthException("DNI already in use!!");
        }
        if(entity.getSpecializations() == null){
            throw new QAuthException("Debe ingresar los servicios que ofrece el empleado");
        }
        return super.save(entity);
    }

    @Override
    public UserDao<Employee> getDAO() {
        return dao;
    }
}