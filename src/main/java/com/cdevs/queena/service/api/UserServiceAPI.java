package com.cdevs.queena.service.api;

import com.cdevs.queena.commons.GenericServiceApi;
import com.cdevs.queena.exceptions.QAuthException;
import com.cdevs.queena.model.User;

public interface UserServiceAPI<T extends User> extends GenericServiceApi <T,Long>{
    public T getByEmail(String email) throws QAuthException;
    public T validateUser(String email, String password) throws QAuthException;
}
