package com.cdevs.queena.service.impl;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cdevs.queena.commons.GenericServiceImpl;
import com.cdevs.queena.dao.EmployeeDaoApi;
import com.cdevs.queena.exceptions.QAuthException;
import com.cdevs.queena.model.Employee;
import com.cdevs.queena.service.api.EmployeeServiceAPI;
import com.cdevs.queena.validations.UserValidations;

@Service
public class EmployeeServiceImpl extends GenericServiceImpl<Employee,Long> implements EmployeeServiceAPI{
    @Autowired
    private EmployeeDaoApi dao;

    @Override
    public CrudRepository<Employee, Long> getDAO() {
        return dao;
    }

    @Override
    public Employee getByDni(Long id) {
        return dao.getEmployeeByDni(id);
    }

    @Override
    public Employee getByEmail(String email) throws QAuthException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Employee validateEmployee(String email, String password) throws QAuthException {
        Employee e = dao.getUserByEmail(email);
        if(e == null || !BCrypt.checkpw(password, e.getPassword()))
            throw new QAuthException("Invalid email/password") ;
        return e;
    }

    @Override
    public Employee save(Employee entity) {
        System.out.println("!!!!"+entity.getId());
        if(!UserValidations.validateEmailPattern(entity.getEmail()))
            throw new QAuthException("Invalid email format");
        if(dao.getUserByEmail(entity.getEmail()) != null){
            throw new QAuthException("Email already in use");
        }
        if(dao.getEmployeeByDni(entity.getDni())!= null){
            throw new QAuthException("DNI already in use");
        }
        System.out.println(entity.getPassword());
        String hashedPassword = BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt(10));
        entity.setPassword(hashedPassword);
        return super.save(entity);
    }
    
}