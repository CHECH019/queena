package com.cdevs.queena.iServices;

import java.util.List;

import com.cdevs.queena.model.Client;

public interface IClientService {
    public List<Client> getList();
    public Client getById(long id);
    public Client save(Client c);
    public void delete(long id);
    public Client update(Client c);
}
