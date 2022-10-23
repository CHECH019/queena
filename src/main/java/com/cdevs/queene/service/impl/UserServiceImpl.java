package com.cdevs.queene.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdevs.queene.dao.UserDao;
import com.cdevs.queene.exceptions.QAuthException;
import com.cdevs.queene.generics.GenericServiceImpl;
import com.cdevs.queene.global.Constants;
import com.cdevs.queene.model.Employee;
import com.cdevs.queene.model.MyService;
import com.cdevs.queene.model.User;
import com.cdevs.queene.service.api.UserServiceAPI;
import com.cdevs.queene.validations.UserValidations;


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
    public List<User> getbyServicesList(List<MyService> services){
        List <Integer> servicesID = new ArrayList<>();
        services.forEach(serv -> servicesID.add(serv.getId() ));
        return dao.findUserByServicesList(servicesID, servicesID.size());
    }
    public List<User> getByRole(String role){
        return dao.getUserByRole(role);
    }

    public UserDao getDAO() {
        return dao;
    }

}