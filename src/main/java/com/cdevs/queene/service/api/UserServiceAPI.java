package com.cdevs.queene.service.api;

import java.util.List;

import com.cdevs.queene.exceptions.QAuthException;
import com.cdevs.queene.generics.GenericServiceApi;
import com.cdevs.queene.model.MyService;
import com.cdevs.queene.model.User;

public interface UserServiceAPI extends GenericServiceApi <User,Long>{
    public User getByEmail(String email) throws QAuthException;
    public User validateUser(String email, String password) throws QAuthException;
    public List<User> getByRole(String role);
    public List<User> getbyServicesList(List<MyService> services);
}
