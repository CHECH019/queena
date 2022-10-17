package com.cdevs.queena.service.api;

import java.util.List;

import com.cdevs.queena.exceptions.QAuthException;
import com.cdevs.queena.generics.GenericServiceApi;
import com.cdevs.queena.model.MyService;
import com.cdevs.queena.model.User;

public interface UserServiceAPI extends GenericServiceApi <User,Long>{
    public User getByEmail(String email) throws QAuthException;
    public User validateUser(String email, String password) throws QAuthException;
    public List<User> getByRole(String role);
    public List<User> getbyServicesList(List<MyService> services);
}
