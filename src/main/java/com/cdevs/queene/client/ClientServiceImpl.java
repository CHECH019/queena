package com.cdevs.queene.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cdevs.queene.exceptions.ResourceNotFoundException;
import com.cdevs.queene.generics.GenericServiceImpl;

@Service
public class ClientServiceImpl extends GenericServiceImpl<Client,Long> implements ClientService{
    
    @Autowired
    private ClientDAO dao;
    
    @Override
    public CrudRepository<Client, Long> getDAO() {
        return dao;
    }

    @Override
    public Client update(Client entity) {
        Client oldClient = dao.findById(entity.getId()).orElseThrow();
        oldClient.setName(entity.getName());
        oldClient.setLastName(entity.getLastName());
        oldClient.setAddress(entity.getAddress());
        oldClient.setPhoneNumber(entity.getPhoneNumber());

        return dao.save(entity);
    }

    @Override
    public ClientDTO getClientDTOById(Long id){
        return dao.findById(id)
                .map(client -> ClientDTO
                    .builder()
                    .name(client.getName())
                    .lastName(client.getLastName())
                    .address(client.getAddress())
                    .phoneNumber(client.getPhoneNumber())
                    .build())
                .orElseThrow(()->new ResourceNotFoundException("Couldn't find any Client with id: "+id));
    }
    
}
