package com.cdevs.queena.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cdevs.queena.model.Client;
import com.cdevs.queena.service.api.ClientServiceAPI;

@RestController
@RequestMapping("api/v1/")
public class ClientRestController {

    @Autowired
    private ClientServiceAPI service;

    @GetMapping("/all-clients")
    public List<Client> getAll(Model model){
        return service.getAll();
    }

    @PostMapping("/save-client")
    public ResponseEntity<Client> save(@RequestBody Client client){
        Client c = service.save(client); 
        return new ResponseEntity<Client>(c, HttpStatus.OK);
    }

    @GetMapping("/find-client/{id}")
    public ResponseEntity<Client> get(@PathVariable Long id, Model model){
        Client c = service.get(id);
        if(c != null)
            service.get(id);
        else
            return new ResponseEntity<Client>(c,HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<Client>(c,HttpStatus.OK);
    }

    @GetMapping("/delete-client/{id}")
    public ResponseEntity<Client> delete(@PathVariable Long id){
        Client c = service.get(id);
        if(c != null)
            service.delete(id);
        else
            return new ResponseEntity<Client>(c,HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<Client>(c,HttpStatus.OK);
    }
}