package com.cdevs.queene.client;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdevs.queene.utils.global.Constants;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.BASE_URL+"/clients")
public class ClientController {

    private final ClientService service;

    @GetMapping({"","/"})
    public List<Client> clients(){
        List<Client> c = service.getAll();
        return c;
    }
    
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client){
        client.setId(id);
        return service.save(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id){
        service.deleteById(id);
    }
}
