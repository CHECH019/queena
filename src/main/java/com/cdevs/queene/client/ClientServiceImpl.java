package com.cdevs.queene.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cdevs.queene.generics.GenericServiceImpl;

@Service
public class ClientServiceImpl extends GenericServiceImpl<Client,Long> implements ClientService{
    
    @Autowired
    private ClientDao dao;
    
    @Override
    public CrudRepository<Client, Long> getDAO() {
        return dao;
    }

    @Override
    public Client save(Client entity) {
        Client oldClient = dao.findById(entity.getId()).orElseThrow();
        oldClient.setName(entity.getName());
        oldClient.setLastName(entity.getLastName());
        oldClient.setAddress(entity.getAddress());
        oldClient.setPhoneNumber(entity.getPhoneNumber());

        return super.save(oldClient);
    }
    
}
