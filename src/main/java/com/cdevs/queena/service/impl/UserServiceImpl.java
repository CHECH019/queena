package com.cdevs.queena.service.impl;

import org.mindrot.jbcrypt.BCrypt;

import com.cdevs.queena.commons.GenericServiceImpl;
import com.cdevs.queena.dao.UserDao;
import com.cdevs.queena.exceptions.QAuthException;
import com.cdevs.queena.model.User;
import com.cdevs.queena.service.api.UserServiceApi;
import com.cdevs.queena.validations.UserValidations;

public abstract class UserServiceImpl<T extends User> extends GenericServiceImpl<T,Long> implements UserServiceApi<T>{

    @Override
    public T getByEmail(String email) throws QAuthException {
        return getDAO().getUserByEmail(email);
    }

    @Override
    public T validateUser(String email, String password) throws QAuthException {
        T c = getDAO().getUserByEmail(email);
        if(c == null || !BCrypt.checkpw(password, c.getPassword()))
            throw new QAuthException("Invalid email/password") ;
        return c;
    }

    
    @Override
    public T save(T entity) {
        if(!UserValidations.validateEmailPattern(entity.getEmail()))
            throw new QAuthException("Invalid email format");
        if(getDAO().getUserByEmail(entity.getEmail()) != null){
            throw new QAuthException("Email already in use");
        }
        String hashedPassword = BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt(10));
        entity.setPassword(hashedPassword);
        return super.save(entity);
    }
    
    public abstract UserDao<T> getDAO() ;
    
}
