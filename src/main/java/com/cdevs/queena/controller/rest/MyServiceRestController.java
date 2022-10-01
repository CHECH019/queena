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

import com.cdevs.queena.model.MyService;
import com.cdevs.queena.service.api.MyServiceServiceAPI;

@RestController
@RequestMapping("api/v1/")
public class MyServiceRestController {
    
    @Autowired
    private MyServiceServiceAPI service;

    @GetMapping("/all-services")
    public List<MyService> getAll(Model model){
        return service.getAll();
    }

    @PostMapping("/save-service")
    public ResponseEntity<MyService> save(@RequestBody MyService myService){
        MyService s = service.save(myService); 
        return new ResponseEntity<MyService>(s, HttpStatus.OK);
    }

    @GetMapping("/find-service/{id}")
    public ResponseEntity<MyService> get(@PathVariable Integer id, Model model){
        MyService s = service.get(id);
        if(s != null)
            service.get(id);
        else
            return new ResponseEntity<MyService>(s,HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<MyService>(s,HttpStatus.OK);
    }

    @GetMapping("/delete-service/{id}")
    public ResponseEntity<MyService> delete(@PathVariable Integer id){
        MyService s = service.get(id);
        if(s != null)
            service.delete(id);
        else
            return new ResponseEntity<MyService>(s,HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<MyService>(s,HttpStatus.OK);
    }
}
