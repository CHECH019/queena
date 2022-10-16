package com.cdevs.queena.service.impl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdevs.queena.dao.UserDao;
import com.cdevs.queena.exceptions.QAuthException;
import com.cdevs.queena.generics.GenericServiceImpl;
import com.cdevs.queena.global.Constants;
import com.cdevs.queena.model.Employee;
import com.cdevs.queena.model.User;
import com.cdevs.queena.service.api.UserServiceAPI;
import com.cdevs.queena.validations.UserValidations;


@Service
public class UserServiceImpl extends GenericServiceImpl<User,Long> implements UserServiceAPI{

    @Autowired
    private UserDao dao;

    @Override
    public User getByEmail(String email) throws QAuthException {
        return dao.getUserByEmail(email);
    }

    @Override
    public User validateUser(String email, String password) throws QAuthException {
        User c = dao.getUserByEmail(email);
        if(c == null || !BCrypt.checkpw(password, c.getPassword()))
            throw new QAuthException("Invalid email/password") ;
        return c;
    }

    @Override
    public User save(User entity) {
       
        if(dao.getUserByEmail(entity.getEmail()) != null){
            throw new QAuthException("Email already in use");
        }
        if(!UserValidations.validateEmailPattern(entity.getEmail()))
            throw new QAuthException("Invalid email format");
        if(entity.getUserRole() == Constants.ROLE_EMPLOYEE){
            Employee e =(Employee) entity;
            Long dni = e.getDni();
            if(dni != null && dao.getUserByDNI(dni) != null){
                throw new QAuthException("DNI already in use");
            }
            if(e.getSpecializations() == null){
                throw new QAuthException("Employee specializations must be provided");
            }
        }
       
        String hashedPassword = BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt(10));
        entity.setPassword(hashedPassword);
        return super.save(entity);
    }
    
    public List<User> getByRole(String role){
        return dao.getUserByRole(role);
    }

    public UserDao getDAO() {
        return dao;
    }
    
}