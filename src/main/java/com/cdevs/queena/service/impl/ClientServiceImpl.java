package com.cdevs.queena.service.impl;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdevs.queena.commons.GenericServiceImpl;
import com.cdevs.queena.dao.ClientDaoApi;
import com.cdevs.queena.exceptions.QAuthException;
import com.cdevs.queena.model.Client;
import com.cdevs.queena.service.api.ClientServiceAPI;
import com.cdevs.queena.validations.UserValidations;

@Service
@Transactional
public class ClientServiceImpl extends GenericServiceImpl<Client, Long> implements ClientServiceAPI{
    @Autowired
    private ClientDaoApi dao;

    @Override
    public CrudRepository<Client,Long> getDAO() {
        return dao;
    }

    @Override
    public Client getByEmail(String email) {
        return dao.getUserByEmail(email);
    }

    @Override
    public Client validateClient(String email, String password) throws QAuthException {
        Client c = dao.getUserByEmail(email);
        if(c == null || !BCrypt.checkpw(password, c.getPassword()))
            throw new QAuthException("Invalid email/password") ;
        return c;
    }

    @Override
    public Client save(Client entity) {
        if(!UserValidations.validateEmailPattern(entity.getEmail()))
            throw new QAuthException("Invalid email format");
        if(dao.getUserByEmail(entity.getEmail()) != null){
            throw new QAuthException("Email already in use");
        }
        String hashedPassword = BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt(10));
        entity.setPassword(hashedPassword);
        return super.save(entity);
    }
    
}