package com.cdevs.queena.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdevs.queena.iServices.IClientService;
import com.cdevs.queena.model.Client;
import com.cdevs.queena.repository.ClientRepository;

@Service
public class ClientService implements IClientService{
    @Autowired
    private ClientRepository repository;

    @Override
    public Client save(Client c) {
        return repository.save(c);
    }

    @Override
    public List<Client> getList() {
        return (List<Client>) repository.findAll();
    }
    
    @Override
    public Client getById(long id) {
        return repository.findById(id).get();
    }
    
    @Override
    public Client update(Client c) {
        return repository.save(c);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
    
}