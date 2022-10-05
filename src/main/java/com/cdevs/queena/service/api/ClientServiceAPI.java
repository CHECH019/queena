package com.cdevs.queena.service.api;


import com.cdevs.queena.commons.GenericServiceApi;
import com.cdevs.queena.model.Client;
import com.cdevs.queena.exceptions.QAuthException;

public interface ClientServiceAPI extends GenericServiceApi<Client,Long>{
    public Client getByEmail(String email) throws QAuthException;
    public Client validateClient(String email, String password) throws QAuthException;
}
