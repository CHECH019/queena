package com.cdevs.queena.service.api;

import com.cdevs.queena.commons.GenericServiceApi;
import com.cdevs.queena.exceptions.QAuthException;
import com.cdevs.queena.model.User;

public interface UserServiceAPI extends GenericServiceApi<User,Long>{
    public User getByEmail(String email) throws QAuthException;
    public User validateUser(String email, String password) throws QAuthException;

}