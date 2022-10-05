package com.cdevs.queena.restController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdevs.queena.commons.GenericServiceApi;
import com.cdevs.queena.model.Client;
import com.cdevs.queena.service.api.ClientServiceAPI;

@RestController
@RequestMapping(value = GenericRestController.BASE_URL + "client")
public class ClientRestController extends GenericRestController<Client,Long>{

    @Autowired
    private ClientServiceAPI service;
    
    @Override
    public GenericServiceApi<Client, Long> getService() {
        return service;
    }

    @PostMapping("/login")
    public ResponseEntity<Client> login(@RequestBody Map<String, Object>clientMap) {
        String email = (String) clientMap.get("email");
        String pass = (String) clientMap.get("password");
        Client c =service.validateClient(email, pass);
        return new ResponseEntity<Client>(c, HttpStatus.OK) ;
    }

}